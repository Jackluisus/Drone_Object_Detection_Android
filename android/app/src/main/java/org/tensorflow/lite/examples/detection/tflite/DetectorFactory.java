package org.tensorflow.lite.examples.detection.tflite;

import android.content.res.AssetManager;

import java.io.IOException;

public class DetectorFactory {
    public static YoloV5Classifier getDetector(
            final AssetManager assetManager,
            final String modelFilename)
            throws IOException {
        String labelFilename = null;
        boolean isQuantized = false;
        int inputSize = 0;
        int[] output_width = new int[]{0};
        int[][] masks = new int[][]{{0}};
        int[] anchors = new int[]{0};

        if (modelFilename.equals("yolov5s.tflite")) {
            labelFilename = "file:///android_asset/coco.txt";
            isQuantized = false;
            inputSize = 640;
            output_width = new int[]{80, 40, 20};
            masks = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
            anchors = new int[]{
                    10,13, 16,30, 33,23, 30,61, 62,45, 59,119, 116,90, 156,198, 373,326
            };
        }
        else if (modelFilename.equals("yolov5s_visdrone-fp16.tflite")) {
            labelFilename = "file:///android_asset/visdrone.txt";
            isQuantized = false;
            inputSize = 640;
            output_width = new int[]{80,40,20};
            masks = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
            anchors = new int[]{
                    3,2, 4,4, 8,4, 6,8, 13,7, 10,13, 21,11, 16,21, 34,26
            };
        }
        else if (modelFilename.equals("yolov5s_visdrone-int8.tflite")) {
            labelFilename = "file:///android_asset/visdrone.txt";
            isQuantized = true;
            inputSize = 640;
            output_width = new int[]{80, 40, 20};
            masks = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
            anchors = new int[]{
                    3,2, 4,4, 8,4, 6,8, 13,7, 10,13, 21,11, 16,21, 34,26
            };
        }
        return YoloV5Classifier.create(assetManager, modelFilename, labelFilename, isQuantized,
                inputSize);
    }

}
