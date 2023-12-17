package com.exam.thecoffeezone.controller;

import com.exam.thecoffeezone.model.ClientTable;
import com.exam.thecoffeezone.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // List all Items
    @GetMapping
    public String createItemForm(Model model) {
        ClientTable clientTable = new ClientTable();
        List<ClientTable> clientTables = clientService.findAllClientTables();
        model.addAttribute("clientTables", clientTables);
        model.addAttribute("clientTable", clientTable);
        return "/clientTable";
    }

    // Save a new Category
    @PostMapping("/new")
    public String saveClientTable(@ModelAttribute("clientTable") ClientTable clientTable) {
        clientService.saveClientTable(clientTable);
        return "redirect:/clients";
    }

    // Find Item by ID
    @GetMapping("/{clientID}/edit")
    public String editItemForm(@PathVariable int clientID, Model model) {
        ClientTable clientTable = clientService.findByID(clientID);
        model.addAttribute("clientTable", clientTable);
        model.addAttribute("clientID", clientID); // Add this line to pass ItemId to the view
        return "updateClientForm";
    }

    // Update Item
    @PostMapping("/{clientID}/update")
    public String updateClient(@PathVariable int clientID, @ModelAttribute("clientTable") ClientTable updatedClientTable, RedirectAttributes redirectAttributes) {
        try {
            clientService.updateClientTable(clientID, updatedClientTable);
            return "redirect:/clients";
        } catch (RuntimeException e) {
            // Handle the exception (log it or add an error message to the redirect attributes)
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/clients";
        }
    }

    // Delete Item
    @GetMapping("/{clientID}/delete")
    public String deleteClient(@PathVariable int clientID) {
        clientService.deleteClientTable(clientID);
        return "redirect:/clients";
    }
}
