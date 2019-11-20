package work.payne.ImageStuff

import java.awt.image.{BufferedImage, Raster}
import java.io.{File, InputStream}
import javax.imageio.ImageIO

class ImageManipulation {




  def run(): Unit = {


    val filepath = new File("src/main/resources/whiteboard.jpg").getAbsolutePath
    println(filepath)
    var image = ImageIO.read(new File("src/main/resources/whiteboard.jpg"))


    val outputImage = processImage(image)

      ImageIO.write(outputImage,"jpeg",new File("test.jpg"))

  }



  def processImage(image: BufferedImage): BufferedImage = {
    val copyImage = deepCopy(image)

    val pixelData: Raster = copyImage.getData
    ???
  }






  def deepCopy(bi: BufferedImage): BufferedImage = {
    val cm = bi.getColorModel
    val isAlphaPremultiplied = cm.isAlphaPremultiplied
    val raster = bi.copyData(null)
    new BufferedImage(cm, raster, isAlphaPremultiplied, null)
  }
}


