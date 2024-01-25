package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
         model.addAttribute("events", EventData.getAll());
//        List<String> events = new ArrayList<>();
//        events.add("Code With Pride");
//        events.add("Strange Loop");
//        events.add("Apple WWDC");
//        events.add("SpringOne Platform");
//        model.addAttribute("events", events);
        return "events/index";
    }

   @GetMapping("create")
    public String renderCreateEventForm() {

        return "events/create";
    }

@PostMapping("create")
    public String createEvent(@ModelAttribute Event newEvent) {
      EventData.add(newEvent);
      return "redirect:/events";
    }


@GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title","Delete Events");
        model.addAttribute("events",EventData.getAll());
       return "events/delete";
}


@PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIDs) {
        if (eventIDs != null) {
            for (int id : eventIDs) {
                EventData.remove(id);
            }
        }

        return "redirect:/events";
}

}
