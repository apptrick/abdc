					PDF Cropper Library.
This library provides you fetures i.e. Image cropping, Image Magnifier, Image filters(Shadow Removal etc.)

How to use???

First of all you have to import this library into your project.

After successfully import PDF Cropper Library, please follow below steps:

1- Make sure your project's 'minSdkVersion' is not less than 21.

2- Add this maven { url 'https://jitpack.io' } line into your project's build.gradle file i.e.
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}

3- Declare below line in that Class/Activity/Fragment where you are capturing/loading pictures, before Camera/Gallery intent:

		net.kuama.pdfcropper.Utils.initializeLibs(context);

4- Create a RelativeLayout in your desired .xml file and position it where you want to display/load image after capturing/loading.

5- Set the above mentioned RelativeLayout to below method to get display of resultant image:

		 net.kuama.pdfcropper.Utils.setCropView(relativeLayout, context);

6- After capturing the image from Camera or loading from gallery, pass the Bitmap of Image to below function:

			 net.kuama.pdfcropper.Utils.setImage(bitmap);

7- After set the edges of image, you need to call the below function to get final Image:

			bitmap = Utils.getCroppedBitmap(); //This is your final bitmap now display it into your own Imageview.

		
			Follow the below steps to set filters at your Image

8- Declare object of net.kuama.pdfcropper.Utils class:

				Utils utils = new Utils();

9- To set Gray effect on Image use:
 
			utils.ImageEffects(bitmap, 1);
// '1' is the code for Gray Scale. // 'bitmap' should be same that we got in Point#7.

10- To remove shadow from Image:

				utils.ImageEffects(bitmap, 2);
// '2' is the code for Shadow Remove Effect you can also use '3' and '4' to get multiple effects of shadow removal. // 'bitmap' should be same that we got in Point#7.