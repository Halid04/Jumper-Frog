const cameraWidth = 390;
const cameraHeight = 550;
const birdWidth = 34;
const birdHeight = 24;
const birdAnimationSpeed = 0.1;
const groundHeight = 100;
const groundWidth = cameraWidth;
const groundAnimationSpeed = 0.1;
let gravity = 9.8;
let start = false;

let app = new PIXI.Application({
  width: cameraWidth,
  height: cameraHeight,
});
document.body.appendChild(app.view);

const background = new PIXI.Sprite(
  PIXI.Texture.from("Image/background-day.png")
);
background.width = cameraWidth + 100;
background.height = cameraHeight;
app.stage.addChild(background);

const birdSpriteImages = [
  "Image/yellowbird-midflap.png",
  "Image/yellowbird-downflap.png",
  "Image/yellowbird-upflap.png",
];
const birdTextureArray = [];

for (let i = 0; i < 3; i++) {
  const birdTexture = PIXI.Texture.from(birdSpriteImages[i]);
  birdTextureArray.push(birdTexture);
}

const birdAnimation = new PIXI.AnimatedSprite(birdTextureArray);
birdAnimation.anchor.y = 0.5;
birdAnimation.anchor.x = 0.5;
birdAnimation.x = Math.floor(cameraWidth / 2);
// birdAnimation.y = Math.floor(cameraHeight / 2);

if (!start) {
  let elapsed = 0.0;
  app.ticker.add((delta) => {
    elapsed += delta;
    birdAnimation.y = 50.0 + Math.cos(elapsed / 15) * 5.0 + cameraHeight / 2.8;
  });
}

birdAnimation.animationSpeed = birdAnimationSpeed * 2;
app.stage.addChild(birdAnimation);
birdAnimation.play();

const groundSpriteImages = [
  "Image/base.png",
  "Image/base2.png.png",
  "Image/base1_5.png ",
];
const groundTextureArray = [];

for (let i = 0; i < 2; i++) {
  const groundTexture = PIXI.Texture.from(groundSpriteImages[i]);
  groundTextureArray.push(groundTexture);
}

const groundAnimation = new PIXI.AnimatedSprite(groundTextureArray);
groundAnimation.width = groundWidth;
groundAnimation.anchor.y = 0.5;
groundAnimation.anchor.x = 0.5;
groundAnimation.y = cameraHeight - groundHeight / 2;
groundAnimation.x = groundWidth / 2;

groundAnimation.animationSpeed = groundAnimationSpeed * 1.5;

app.stage.addChild(groundAnimation);
groundAnimation.play();

window.addEventListener("keydown", (e) => {
  if (e.key == " ") {
    start = true;
    ok = true;
  }

  if (start == true) {
    let velocitaPre = birdAnimation.y;

    app.ticker.add((delta) => {
      velocitaPre += delta * (gravity / 2);
      birdAnimation.y = velocitaPre;
      birdAnimation.rotation += 0.04;
    });
  }
});

function birdGroundCollision(sprite1, sprite2) {}

// birdAnimation.y = Math.floor(cameraHeight / 2 - birdHeight / 2);
