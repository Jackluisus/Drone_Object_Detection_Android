import os
import cv2

# 数据集根目录
root_dir = r'F:\Datasets\obj_det\vidrone\visdrone2020\VisDrone2019-DET-val'
annotations_dir = root_dir + "/annotations/"
image_dir = root_dir + "/images/"
# 新的目录
anno_yolov3_dir = root_dir + "/Annotations_yolov5/"

'''
#参考visdrone的所有类别
class_name: ['ignored regions', 'pedestrian', 'people', 'bicycle', 'car', 'van', 'truck', 'tricycle',
              'awning-tricycle', 'bus', 'motor', 'others']
'''

total_num = len(os.listdir(annotations_dir))
num = 0

if not os.path.exists(anno_yolov3_dir):
    os.mkdir(anno_yolov3_dir)

for filename in os.listdir(annotations_dir):
    image_name = filename.split('.')[0]
    img_array = cv2.imread(image_dir + image_name + '.jpg')[:, :, ::-1]
    h, w = img_array.shape[0], img_array.shape[1]
    new_txt_path = os.path.join(anno_yolov3_dir, filename)
    fin = open(annotations_dir + filename, 'r')
    with open(new_txt_path, 'w') as fout:
        for line in fin.readlines():
            line = line.split(',')
            # 指定类别
            if line[5] in ["4", "5", "6", "9"]:  # 选择类别
                line[5] = "0"  # 重置类别序号
                fout.write(str(int(line[5])) + ' ')
                fout.write(str((int(line[0]) + int(line[2]) / 2) / w) + ' ')
                fout.write(str((int(line[1]) + int(line[3]) / 2) / h) + ' ')
                fout.write(str(int(line[2]) / w) + ' ')
                fout.write(str(int(line[3]) / h))
                fout.write('\n')

        num += 1
        print("%s - > %s" % (num, total_num))

print("It is finished.")
