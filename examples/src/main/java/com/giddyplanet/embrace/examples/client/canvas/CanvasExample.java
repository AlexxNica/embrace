package com.giddyplanet.embrace.examples.client.canvas;

import com.giddyplanet.embrace.examples.client.Example;
import com.giddyplanet.embrace.examples.client.Examples;
import com.giddyplanet.embrace.webapis.*;

import static com.giddyplanet.embrace.examples.client.Examples.getDocument;

public class CanvasExample implements Example {

    @Override
    public String getId() {
        return "canvas";
    }

    @Override
    public String getTitle() {
        return "2D rendering with canvas";
    }

    @Override
    public HTMLElement setup() {
        Document document = getDocument();

        HTMLCanvasElement canvas = (HTMLCanvasElement) document.createElement("canvas");
        canvas.width = 400;
        canvas.height = 300;
        canvas.style.setProperty("imageRendering", "crisp");

        CanvasRenderingContext2D ctx = (CanvasRenderingContext2D) canvas.getContext("2d");
        ctx.fillStyle = "#008";
        ctx.fillRect(0, 0, 400, 300);

        ctx.imageSmoothingQuality = ImageSmoothingQuality.HIGH;

        ctx.fillStyle = "#ff0";
        ctx.beginPath();
        ctx.arc(200, 150, 100, 0, 2.0 * Math.PI, false);
        ctx.fill();
        ctx.lineWidth = 4;
        ctx.strokeStyle = "#000";
        ctx.stroke();

        ctx.beginPath();
        ctx.lineWidth = 4;
        ctx.fillStyle = "#000";
        ctx.arc(200, 150, 80, 0, Math.PI, false);
        ctx.stroke();

        ctx.save();
        ctx.scale(1, 1.5);
        ctx.beginPath();
        ctx.arc(170, 90, 20, 0, 2.0 * Math.PI, false);
        ctx.moveTo(250, 100);
        ctx.arc(230, 90, 20, 0, 2.0 * Math.PI, false);
        ctx.fill();
        ctx.restore();

        ctx.fillStyle = "#fff";
        ctx.fillText("Canvas is an element node: " + (canvas.nodeType == Node.ELEMENT_NODE), 10, 20);

        return canvas;
    }

    @Override
    public void start() {

    }
}
