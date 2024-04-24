import ddf.minim.*;

Minim minim;
AudioPlayer dropSound;

ArrayList<Drop> rain;

void setup()
{
  surface.setTitle("Rain Droplets");
  surface.setResizable(false);
  surface.setLocation(displayWidth / 3, floor(0.1 * displayHeight));

  minim = new Minim(this);
  dropSound = minim.loadFile("dropSound.mp3");
  rain = new ArrayList<Drop>();

  //fullScreen();
  size(810, 600);
}

void createDroplet()
{
  var radius = noise(sin(frameRate));

  var posX = random(radius, width - radius);
  var posY = random(radius, height - radius);
  var position = new PVector(posX, posY);

  rain.add(new Drop(position, radius));
}

void draw()
{
  background(0);
  
  if (!dropSound.isPlaying()) dropSound.play();

  createDroplet();
  for (var drop : rain)
  {
    drop.drop();
    drop.show();
  }

  for (int d = 0; d < rain.size(); d++)
  {
    var drop = rain.get(d);

    var hasDropVanished = (drop.lifeSpan <= 0);
    if (hasDropVanished) rain.remove(d);
  }
}
