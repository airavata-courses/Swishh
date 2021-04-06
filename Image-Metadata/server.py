from flask import Flask
from flask import request
import cv2
from PIL import Image
import io
import requests
from Metadata import MetaDataExtractor

app = Flask(__name__)

@app.route('/test', methods=['GET'])
def get():
    print(request.files['image_data'])
    img = request.files['image_data']
    image = cv2.imread(img.filename)
    rows, cols, channels = image.shape
    M = cv2.getRotationMatrix2D((cols/2, rows/2), 90, 1)
    dst = cv2.warpAffine(image, M, (cols, rows))
    cv2.imwrite('output.png', dst)
    ##do all image processing and return json response
    return 'image: success'


if __name__ == '__main__':
    try:
        app.run(port= 5999, debug= True)
    except Exception as e:
        print(e)
