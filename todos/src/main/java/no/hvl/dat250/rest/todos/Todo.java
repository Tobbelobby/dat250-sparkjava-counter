package no.hvl.dat250.rest.todos;

import com.google.gson.Gson;

import java.util.Objects;

public class Todo {
    private final Long id;
    private String summary;
    private String description;

    private static long ID = 0;

    public static synchronized long ID() {
        return ID++;
    };




    public Todo(Long id, String summary, String description) {
        this.id = id;
        this.summary = summary;
        this.description = description;
    }

    public Todo(String summary, String description) {
        this(ID(), summary, description);
    }

    /**
     * ID might be null!
     */
    public Long getId() {
        return id;
    }



    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;

    }

    // Do not change equals and hashcode!

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) && Objects.equals(summary, todo.summary) && Objects.equals(description, todo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, summary, description);
    }


    String toJson () {

        Gson gson = new Gson();

        String jsonInString = gson.toJson(this);

        return jsonInString;
    }
}
