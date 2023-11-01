package ua.sansan.yagrpgapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.sansan.yagrpgapi.entities.geo.World;

@Controller
public class MainPageController {
@Autowired
    World world;

//    public void setWorld(World world) {
//        this.world = world;
//    }

    @GetMapping(value="/home")
    public String getMainPage(Model model){

//        GroovyScriptEvaluator e = new GroovyScriptEvaluator();
//        World w = new World();
//        w.info = new GameObject();
//        w.info.name = "fst";
//        Map<String, Object> arguments = new HashMap<>();
//        arguments.put("w", w);
//        Object evaluate = e.evaluate(new StaticScriptSource("w.info.description = 'desc';return w"), arguments);
//        //GroovyShell s = new GroovyShell();


        model.addAttribute("val", world.info.name);
        return "home";
    }
}
