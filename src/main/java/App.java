import java.util.Map;

import org.eclipse.jetty.http.MetaData.Request;

import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main (String[]args){
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        //create index route
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //view all squads user has created
        get("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("squads", Squad.all());
            model.put("template", "templates/squads.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/squads-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            String name = request.params("name");
            String cause = request.params("cause");
            Integer size = (Integer.parseInt(request.params("size")));

            Squad newTeam = new Squad(name, size, cause);
            model.put("template", "templates/squad-success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}