package com.frederico.firebasefirst.models;

public class TarefaModel {
    private String Id;
    private String Content;
    private String Title;
    private Press press;

    public TarefaModel(String id, String title, String content, Press func) {
        Content = content;
        Title = title;
        press = func;
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public String getTitle() {
        return Title;
    }

    public Press getPress() {
        return press;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getId() {
        return Id;
    }

    public interface Press{
        public void Short(String id);
        public void Long(String id);
    }
}
