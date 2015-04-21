package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.Game.State;
import se.liu.ida.tddd78.towerdefense.exceptions.ThemeLoadException;
import se.liu.ida.tddd78.towerdefense.interfaces.ButtonObserver;
import se.liu.ida.tddd78.towerdefense.interfaces.GameObserver;
import se.liu.ida.tddd78.towerdefense.interfaces.Observer;
import se.liu.ida.tddd78.towerdefense.objects.ButtonType;
import se.liu.ida.tddd78.towerdefense.objects.theme.ThemeLoader;
import se.liu.ida.tddd78.towerdefense.utils.FileDiscoveryUtil;
import se.liu.ida.tddd78.towerdefense.utils.FileDiscoveryUtil.FileType;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Options implements ButtonObserver {
    private State lastState;
    private List<String> availableLayouts;
    private List<String> availableThemes;
    private int layoutIndex;
    private int themeIndex;

    private List<Observer> optionChangeObservers;

    public Options() {
        this.lastState = null;
        this.availableLayouts = new ArrayList<>();
        this.availableThemes = new ArrayList<>();
        this.optionChangeObservers = new ArrayList<>();
    }

    public String getCurrentLayoutName() {
        if (availableLayouts.size() > layoutIndex) {
            return availableLayouts.get(layoutIndex);
        } else {
            return "None";
        }
    }

    public String getCurrentThemeName() {
        if (availableThemes.size() > themeIndex) {
            return availableThemes.get(themeIndex);
        } else {
            return "None";
        }
    }

    private boolean verifyLayout(URL file) {
        return true;
    }

    private boolean verifyTheme(URL file) {
        try {
            ThemeLoader.loadTheme(file);
            return true;
        } catch (ThemeLoadException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getName(URL url) {
        String str = url.getFile();
        return str.substring(str.lastIndexOf('/') + 1, str.lastIndexOf('.'));
    }

    private void reloadFiles() {
        availableLayouts = new ArrayList<>();
        availableThemes = new ArrayList<>();

        List<URL> foundLayouts = FileDiscoveryUtil.retrieveExistingFiles(FileType.LAYOUT);
        for (URL layout : foundLayouts) {
            if (verifyLayout(layout)) {
                availableLayouts.add(getName(layout));
            }
        }

        List<URL> foundThemes = FileDiscoveryUtil.retrieveExistingFiles(FileType.THEME);
        for (URL theme : foundThemes) {
            if (verifyTheme(theme)) {
                availableThemes.add(getName(theme));
            }
        }

        layoutIndex = Math.min(layoutIndex, availableLayouts.size());
        themeIndex = Math.min(themeIndex, availableThemes.size());
    }

    private void applyOptions() {

    }

    private void onStateChanged(State state) {
        if (state == State.OPTIONS_MENU) {
            reloadFiles();
        } else if (state == State.MAIN_MENU) {
            applyOptions();
        }
    }

    /*
    @Override
    public void onNotify(Game game) {
        State currentState = game.getState();
        if (currentState != lastState) {
            onStateChanged(currentState);
        }

        lastState = currentState;
    }
    */

    public static void main(String[] args) {
        Options options = new Options();
        options.reloadFiles();
    }

    @Override
    public void onButtonClicked(ButtonType buttonType) {
        switch (buttonType) {
            case NEXT_MAP:
                layoutIndex++;
                if (layoutIndex >= availableLayouts.size()) {
                    layoutIndex = 0;
                }

                notifyOptionChanged();
                break;
            case NEXT_THEME:
                themeIndex++;
                if (themeIndex >= availableThemes.size()) {
                    themeIndex = 0;
                }

                notifyOptionChanged();
                break;
            case OPTIONS:
                reloadFiles();

                notifyOptionChanged();
                break;
            case MAIN_MENU:
                applyOptions();

                break;
        }
    }

    public void addOptionChangeObserver(Observer observer) {
        optionChangeObservers.add(observer);
        observer.onNotify();
    }

    public void notifyOptionChanged() {
        for (Observer observers : optionChangeObservers) {
            observers.onNotify();
        }
    }
}
