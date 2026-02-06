package Filters;

import Interfaces.Drawable;
import Interfaces.PixelFilter;
import core.DImage;
import processing.core.PApplet;

public class FixedThresholdFilter implements PixelFilter, Drawable {

    private int threshold = 127;


    public FixedThresholdFilter() {}

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                grid[r][c] = (grid[r][c] > threshold) ? (short)255 : (short)0;
            }
        }

        img.setPixels(grid);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        int[] center = ColorMaskFilter.findCenter(filtered.getBWPixelGrid());

        if (center != null) {
            window.fill(255, 0, 0);
            window.ellipse(center[0], center[1], 20, 20);
        }
    }
}
