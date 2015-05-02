package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.exceptions.LayoutParseException;
import se.liu.ida.tddd78.towerdefense.exceptions.ThemeLoadException;
import se.liu.ida.tddd78.towerdefense.exceptions.ThemeReadException;
import se.liu.ida.tddd78.towerdefense.exceptions.ThemeParseException;
import se.liu.ida.tddd78.towerdefense.interfaces.ButtonObserver;
import se.liu.ida.tddd78.towerdefense.interfaces.Observer;
import se.liu.ida.tddd78.towerdefense.entities.ButtonType;
import se.liu.ida.tddd78.towerdefense.entities.layout.Layout;
import se.liu.ida.tddd78.towerdefense.entities.layout.LayoutLoader;
import se.liu.ida.tddd78.towerdefense.entities.layout.LayoutType;
import se.liu.ida.tddd78.towerdefense.entities.theme.Theme;
import se.liu.ida.tddd78.towerdefense.entities.theme.ThemeLoader;
import se.liu.ida.tddd78.towerdefense.entities.theme.ThemeType;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of the game settings, such as map layout, theme and difficulty
 */
public class Options implements ButtonObserver {
    private List<Layout> availableLayouts;
    private List<Theme> availableThemes;
    private int layoutIndex;
    private int themeIndex;

    private Theme theme;
    private Layout layout;
    private static final ThemeType DEFAULT_THEME = ThemeType.STANDARD;
    private static final LayoutType DEFAULT_LAYOUT = LayoutType.STANDARD;

    private List<Observer> optionChangeObservers;

    public Options() throws LayoutParseException, ThemeReadException, ThemeParseException {
        this.optionChangeObservers = new ArrayList<>();

        this.theme = ThemeLoader.load(DEFAULT_THEME);
        this.layout = LayoutLoader.load(DEFAULT_LAYOUT);
        this.reloadFiles();

        layoutIndex = 1;
        themeIndex = 1;
    }

    public Layout getLayout() {
        return this.layout;
    }

    public Theme getTheme() {
        return this.theme;
    }

    //Currently holds all themes & layouts in memory. Should probably be lazy loaded
    //Catches should propagate to the UI an deliver error message to user
    // TODO: Don't abort the entire loading mechanism if one file failed. Use iterator pattern?
    private void reloadFiles() {
        try {
            availableLayouts = LayoutLoader.getAvailableLayouts();
        } catch (LayoutParseException e) {
            e.printStackTrace();
        }
        try {
            availableThemes = ThemeLoader.getAvailableThemes();
        } catch (ThemeLoadException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onButtonClicked(ButtonType buttonType) {
        switch (buttonType) {
            case NEXT_MAP:
                layoutIndex = (layoutIndex + 1) % this.availableLayouts.size();
                this.layout = this.availableLayouts.get(layoutIndex);

                notifyOptionChanged();
                break;
            case NEXT_THEME:
                themeIndex = (themeIndex + 1) % this.availableLayouts.size();
                this.theme = this.availableThemes.get(themeIndex);

                notifyOptionChanged();
                break;
            case OPTIONS:
                reloadFiles();

                notifyOptionChanged();
                break;
            case MAIN_MENU:
                break;
        }
    }

    public void addOptionChangeObserver(Observer observer) {
        optionChangeObservers.add(observer);
    }

    public void notifyOptionChanged() {
        optionChangeObservers.forEach(Observer::onNotify);
    }
}
