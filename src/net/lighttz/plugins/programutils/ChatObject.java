package net.lighttz.plugins.programutils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;

public class ChatObject {
    private String mensagem;
    private HoverEvent hevent;
    private ClickEvent cevent;

    public ChatObject(String mensagem, HoverEvent hevent, ClickEvent cevent) {
        this.setMensagem(mensagem);
        this.setHoverEvent(hevent);
        this.setClickEvent(cevent);
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public HoverEvent getHoverEvent() {
        return this.hevent;
    }

    public void setHoverEvent(HoverEvent event) {
        this.hevent = event;
    }

    public ClickEvent getClickEvent() {
        return this.cevent;
    }

    public void setClickEvent(ClickEvent cevent) {
        this.cevent = cevent;
    }
}

