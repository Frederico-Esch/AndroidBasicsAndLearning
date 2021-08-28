import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Kinda First app',
      theme: ThemeData(
        primarySwatch: Colors.deepPurple,
      ),
      home: MyHomePage(title: 'Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  bool clr = false;
  double height = 0;
  double weight = 0;
  void alternar() {
    setState(() {
      clr = !clr;
    });
  }

  void changedHeight(String? value) {
    value = (value == null || value == "") ? "0" : value;
    try {
      double valueDouble = double.parse(value.toString());
      setState(() {
        height = valueDouble;
      });
    } catch (e) {
      final snackbar = SnackBar(content: Text("Algo deu errado"));
      ScaffoldMessenger.of(this.context).showSnackBar(snackbar);
    }
  }

  void changedWeight(String? value) {
    value = (value == null || value == "") ? "0" : value;
    try {
      double valueDouble = double.parse(value.toString());
      setState(() {
        weight = valueDouble;
      });
    } catch (e) {
      final snackbar = SnackBar(content: Text("Algo deu errado"));
      ScaffoldMessenger.of(this.context).showSnackBar(snackbar);
    }
  }

  String result() {
    double IMC = weight / (height * height);
    if (IMC < 18.5) {
      return "Abaixo do Peso";
    } else if (IMC >= 18.5 && IMC < 24.9) {
      return "Peso Normal";
    } else if (IMC >= 24.9 && IMC < 29.9) {
      return "Sobrepeso";
    } else if (IMC >= 29.9 && IMC < 34.9) {
      return "Obesidade I";
    } else if (IMC >= 34.9 && IMC < 39.9) {
      return "Obesidade II";
    } else if (IMC >= 39.9) {
      return "Obesidade Mórbida";
    }
    return "Inválido";
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Padding(
              padding: EdgeInsets.all(8),
              child: TextField(
                decoration: InputDecoration(
                    border: UnderlineInputBorder(), labelText: "Height"),
                keyboardType: TextInputType.number,
                onChanged: changedHeight,
              ),
            ),
            Padding(
              padding: EdgeInsets.all(8),
              child: TextField(
                decoration: InputDecoration(
                    border: UnderlineInputBorder(), labelText: "Weight"),
                keyboardType: TextInputType.number,
                onChanged: changedWeight,
              ),
            ),
            Padding(
                padding: EdgeInsets.all(16),
                child: Divider(
                  color: Colors.grey,
                )),
            Text(
                'IMC = ${(weight > 0 && height > 0) ? (weight / (height * height)).toStringAsFixed(2) : "Indefinido"}',
                style: new TextStyle(
                    fontSize: 20, color: (clr) ? Colors.red : Colors.black)),
            Text(
              "Result -> ${result()}",
              style: new TextStyle(
                  fontSize: 20, color: (clr) ? Colors.red : Colors.black),
            )
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: alternar,
        tooltip: 'Alternar Cor',
        child: new IconTheme(
            data: new IconThemeData(color: Colors.greenAccent),
            child: Icon(Icons.done_outline)),
        backgroundColor: Colors.deepPurple[900],
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
