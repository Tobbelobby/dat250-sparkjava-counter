package no.hvl.dat250.rest.todos;

import com.google.gson.Gson;

import java.util.HashMap;

import static spark.Spark.*;

/**
 * Rest-Endpoint.
 */
public class TodoAPI {


    private static Boolean isNumeric(String num) {
        try {
            Long.parseLong(num);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }



    public static void main(String[] args) {
        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }

        final TodoSer todo = new TodoDAO();


        after((req, res) -> res.type("application/json"));

        // TODO: Implement API, such that the testcases succeed


        get("/todos", (req, res) -> {
            Gson gson = new Gson();
            System.out.println(gson);
            return gson.toJson(todo.get());
        });

        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });




        get("/todos/:id", (request, response) -> {
            String in_Id = request.params(":id");
            if (isNumeric(in_Id)) {
                Long id = Long.parseLong(in_Id);
                if (todo.getTodo(id) != null) {
                    return new Gson().toJson(new Gson().toJsonTree(todo.getTodo(id)));
                } else {
                    return String.format("Todo with the id  \"%s\" not found!", in_Id);
                }
            } else {
                return String.format("The id \"%s\" is not a number!", in_Id);
            }
        });


        post("/todos", (req, res) -> {
            Todo newTodo = new Gson().fromJson(req.body(), Todo.class);
            todo.add(newTodo);
            return new Gson().toJson(newTodo);
        });

        put("/todos/:id",((request, response) -> {
            String id = request.params(":id");
            if(!(isNumeric(id))){
                return String.format("The id \"%s\" is not a number!", id);
            }
            Todo old = new Gson().fromJson(request.body(), Todo.class);
            Todo todoUp = todo.edit(old);
            if(todoUp !=null){
                return new Gson().toJson(todoUp);
            }else{
                return String.format("Todo with the id  \"%s\" not found!", id);
            }

        }));

        delete("/todos/:id",((request, response) -> {
            String id = request.params(":id");
            if(!(isNumeric(id))){
                return String.format("The id \"%s\" is not a number!", id);
            }
            Long id_long = Long.parseLong(id);
            if(!(todo.todoExist(id_long))){
                return String.format("Todo with the id  \"%s\" not found!", id);
            }else{
                todo.delete(id_long);
                return ("del");
            }}
            ));

        }
}




