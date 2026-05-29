package Ticketera.Console;



import Ticketera.Service.TicketHandler;

import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketHandler ticketHandler = new TicketHandler(scanner);
        ticketHandler.run();
    }
}
