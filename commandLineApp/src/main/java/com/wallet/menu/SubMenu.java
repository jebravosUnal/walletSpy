package com.wallet.menu;

import java.io.IOException;

public abstract class SubMenu extends Menu {

    private Menu previousMenu;

    public SubMenu(Menu previousMenu) {
        this.previousMenu = previousMenu;
    }

    protected void printPreviousMenu() throws IOException {
        previousMenu.printMenu();
    }
}
