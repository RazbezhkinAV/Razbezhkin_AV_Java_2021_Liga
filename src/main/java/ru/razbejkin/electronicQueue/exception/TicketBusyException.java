package ru.razbejkin.electronicQueue.exception;

public class TicketBusyException extends RuntimeException{
    public TicketBusyException() {
        super("Данное время занято\nВыберите другое время");
    }
}
