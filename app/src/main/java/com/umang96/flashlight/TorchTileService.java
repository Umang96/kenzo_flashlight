package com.umang96.flashlight;

import android.content.SharedPreferences;
import android.graphics.drawable.Icon;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class TorchTileService extends TileService {

    @Override
    public void onClick() {
        super.onClick();
        updateTile(2);
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();
        updateTile(1);
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
        updateTile(1);
    }

    private void updateTile(int x) {

        boolean isActive = TorchUtils.check(this,1);
        if(x==2) {
            isActive = TorchUtils.check(this,2);
        }
        Tile tile = this.getQsTile();
        Icon newIcon,alticon;
        int newState = Tile.STATE_ACTIVE;

        if (isActive) {

            newIcon = Icon.createWithResource(getApplicationContext(),
                    R.drawable.ic_signal_flashlight_disable);
            alticon = Icon.createWithResource(getApplicationContext(),
                    R.drawable.ic_signal_flashlight_enable);


        } else {
                    newIcon =
                            Icon.createWithResource(getApplicationContext(),
                                    R.drawable.ic_signal_flashlight_enable);
            alticon = Icon.createWithResource(getApplicationContext(),
                    R.drawable.ic_signal_flashlight_disable);
        }

        tile.setLabel(getTileName());
        if(x==2) {
            tile.setIcon(newIcon);
        }
        if(x==1) {
            tile.setIcon(alticon);
        }
        tile.setState(newState);
        tile.updateTile();
    }

    public String getTileName() {
        SharedPreferences prefs = getSharedPreferences("tile_preferences", MODE_PRIVATE);
        String restoredText = prefs.getString("tile_name", null);
        String temp="Flashlight";
        if (restoredText != null) {
            temp = prefs.getString("tile_name", "Flashlight");
        }
        return temp;
    }

}
