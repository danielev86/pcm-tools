package com.developwithhomer.pcmexporter.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.model.menu.*;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named("menuController")
public class MenuBackingBean extends CommonBackingBean {

    @Serial
    private static final long serialVersionUID = -2267467823527966065L;

    private MenuModel menuModel;

    @PostConstruct
    public void init() {
        menuModel = new DefaultMenuModel();
        menuModel.getElements().add(buildDefaultMenu("homeMenuId", getMessage("pcmmenu.submenu.home"), "/home.xhtml"));

        menuModel.getElements().add(buildDefaultMenu("menuId", "Menu", "menu.xhtml"));
        menuModel.getElements().add(buildMyTeamSubMenu());
        menuModel.getElements().add(buildCyclistSubMenu());
        menuModel.getElements().add(buildTeamMenu());
        menuModel.getElements().add(buildCheatMenu());
    }

    private DefaultSubMenu buildMyTeamSubMenu(){
        List<MenuElement> menuElements = new ArrayList<>();
        menuElements.add(buildDefaultMenu("myTeamSummary", getMessage("pcmmenu.submenu.myteam.summary"), "/myTeamSummary.xhtml"));
        return buildDefaultSubMenu("myTeamId", getMessage("pcmmenu.submenu.myteam.summary"), menuElements);
    }

    private DefaultSubMenu buildCyclistSubMenu(){
        List<MenuElement> menuElements = new ArrayList<>();
        return buildDefaultSubMenu("ciclystMenuId", getMessage("pcmmenu.submenu.cyclists"), menuElements);
    }

    private DefaultSubMenu buildTeamMenu(){
        List<MenuElement> menuElements = new ArrayList<>();
        return buildDefaultSubMenu("teamMenuId", getMessage("pcmmenu.submenu.team"), menuElements);
    }

    private DefaultSubMenu buildCheatMenu(){
        List<MenuElement> menuElements = new ArrayList<>();
        return buildDefaultSubMenu("myTeamId", getMessage("pcmmenu.submenu.cheat"), menuElements);
    }

    private DefaultMenuItem buildDefaultMenu(String id, String menuName, String url){
        DefaultMenuItem menu = new DefaultMenuItem();
        menu.setId(id);
        menu.setTitle(menuName);
        menu.setUrl(url);
        return menu;
    }

    private DefaultSubMenu buildDefaultSubMenu(String id, String menuName, List<MenuElement> menuItems){
        DefaultSubMenu subMenu = new DefaultSubMenu();
        subMenu.setId(id);
        subMenu.setLabel(menuName);
        subMenu.setElements(menuItems);
        return subMenu;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }
}
