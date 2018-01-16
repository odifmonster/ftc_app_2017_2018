# Winsor Wildbots FTC

Hey Wildbots coders! So almost everything you need to know about the app is in the
official FTC README file, but seeing as we've made a few changes to the code
and have added various libraries and dependencies, it seems like a good idea
to keep this updated, especially for people who want to learn to code and are
added to the repo later. This will be a basic description of what we've
changed, extra info that might not be in the other README, and anything else
to help out current and future coders.

## Downloading the Project

Yes yes, this section is in the other README, but what the other file doesn't
tell you is that after you git clone this project and import it to Android
Studio, you will get an error that you probably don't understand. Fortunately,
because the IDE is so clever, it offers an immediate solution. Basically, keep
clickling those blue links under the error messages until there are no more.
If you add more dependencies and libraries further down the line, you may
discover that the gradle files will force you to go through this process a few
times.

### Windows Users

I haven't figured out how to deal with this yet, but just know that if you use
Windows everything will break which is why Apple is better. Just get Apple
products.

## Current Dependencies

We are currently using DogeCV (I know, but it's legit I swear), which is based
off of OpenCV and EnderCV. The CV in all of these stands for computer vision
which is exactly what it sounds like. I won't get into the specifics (although
you can research it if you want, it's very cool), but here is a simple
breakdown:

* OpenCV is a computer vision library used by everyone and allows for a lot of
freedom but requires a fairly extensive understanding of computer vision and
Android Studio in order to implement correctly

* EnderCV is based off of OpenCV and provides help for using OpenCV in the
context of the FTC app (so if you're not an Android Developer you can just use
their code), but actually detecting the objects is something you have to
figure out yourself. We plan on using this in the future, but right now we
simply do not have the time.

* DogeCV comes with the OpenCV320 library (as does ender), but they have a lot
of Relic Recovery-specific stuff builtin, so it's a simple solution for now.
Everything is prepackaged, so if you think their algorithms aren't all that
impressive, you should only be using it if you really have no time.

## Current Libraries

Most of these will change from year to year, but it's still good to explain,
and the driving library probably won't need to change

### Driving Library

This was configured for mecanum wheels, so if future drive trains may need to
reconfigure some of this. I don't personally know the algorithm for omni
wheels, but I believe it remains somewhat the same.
