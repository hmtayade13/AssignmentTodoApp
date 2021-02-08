package com.assignment.todoapp.controllers;

import com.assignment.todoapp.models.Tasks;
import com.assignment.todoapp.models.User;
import com.assignment.todoapp.repositories.TasksRepository;
import com.assignment.todoapp.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class TasksController {

    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private TasksRepository noteRepository;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ModelAndView notes() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("tasks", noteRepository.findAllByUser(user.getEmail()));
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("tasks");
        return modelAndView;
    }

    @RequestMapping("/tasks/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("create");
        return modelAndView;
    }

    @RequestMapping("/tasks/save")
    public String save(@RequestParam String title, @RequestParam String content) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Tasks note = new Tasks();
        note.setUser(user.getEmail());
        note.setTitle(title);
        note.setContent(content);
        note.setUpdated(new Date());
        note.setStatus(false);
        noteRepository.save(note);

        return "redirect:/tasks";
    }

    @RequestMapping("/tasks/delete")
    public String delete(@RequestParam Long id) {
        Tasks note = noteRepository.findById(id).orElse(null);
        noteRepository.delete(note);

        return "redirect:/tasks";
    }

    @RequestMapping("/tasks/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.addObject("task", noteRepository.findById(id).orElse(null));
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @RequestMapping("/tasks/update")
    public String update(@RequestParam Long id, @RequestParam String title, @RequestParam String content, @RequestParam(required = false) boolean status) {
        Tasks note = noteRepository.findById(id).orElse(null);
        note.setTitle(title);
        note.setContent(content);
        note.setUpdated(new Date());
        note.setStatus(status);
        noteRepository.save(note);
        return "redirect:/tasks";
    }

}
