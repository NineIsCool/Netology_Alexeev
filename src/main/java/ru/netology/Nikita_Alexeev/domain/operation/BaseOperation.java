package ru.netology.Nikita_Alexeev.domain.operation;

import java.time.LocalDateTime;

public abstract class BaseOperation {
    private int id;
    private LocalDateTime time;

    public abstract void printToConsole();



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseOperation{" +
                "id=" + id +
                ", time=" + time +
                '}';
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
