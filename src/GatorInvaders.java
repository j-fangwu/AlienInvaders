import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class GatorInvaders {
    static ArrayList<GameObject> aliens = new ArrayList<>();
    static ArrayList<GameObject> health = new ArrayList<>();

    static void Start() {
        GatorEngine.OBJECTLIST.clear();

        Color BrightGreen = new Color(0, 255, 0, 100);

        GameObject StartScreen = new GameObject(0, 0);
        StartScreen.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 0.0F, 0.0F);
        StartScreen.material = new Material("resources/Title.png");
        GatorEngine.Create(StartScreen);
        StartScreen.Scale(0.5f, 0.5f);

        GameObject StartBox = new GameObject(0, 0);
        StartBox.shape = new java.awt.geom.Rectangle2D.Float(202.0F, 313.0F, 98.0F, 51.0F);
        StartBox.material = new Material(BrightGreen, BrightGreen, 2);
        StartBox.scripts.add(new MouseHoverStart(StartBox));
        GatorEngine.Create(StartBox);
    }

    static void LevelOne() {
        GatorEngine.UpdateObjectList();
        GatorEngine.OBJECTLIST.clear();
        Input.UpdateInputs();
        aliens.clear();
        health.clear();
        Random r = new Random();
        int randomPower = r.nextInt(400);

        Color Diamond = new Color(84, 214, 172);

        GameObject Background = new GameObject(0, 0);
        Background.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 0.0F, 0.0F);
        Background.material = new Material("resources/Background.png");
        GatorEngine.Create(Background);
        Background.Scale(0.5f, 0.5f);

        for (int i = 2; i >= 0; i--) {
            GameObject healthPoint = new GameObject((35 * i) + 10 , 460);
            healthPoint.material = new Material("resources/Ship.png");
            healthPoint.Scale(0.9f, 0.9f);
            GatorEngine.Create(healthPoint);
            health.add(healthPoint);
        }

        GameObject Player = new GameObject(238, 400);
        Player.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 30.0F, 50.0F);
        Player.material = new Material("resources/Ship.png");
        Player.scripts.add(new PlayerMovement(Player));
        Player.scripts.add(new PlayerAttack(Player, aliens, 30));
        Player.Scale(1.5f, 1.5f);
        GatorEngine.Create(Player);

        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < 6; j++) {
                GameObject Enemy = new GameObject(j * 50, i * 50);
                Enemy.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 25.0F, 25.0F);
                Enemy.transform.translate(75, -25);
                Enemy.material = new Material("resources/Enemy1.png");
                Enemy.Scale(1.5f, 1.5f);
                Enemy.scripts.add(new EnemyMovement(Enemy));
                Enemy.scripts.add(new EnemyAttack(Enemy, Player, health));
                aliens.add(Enemy);
                GatorEngine.Create(Enemy);
            }
        }

        if (r.nextDouble() < 0.3) {
            GameObject PowerUp = new GameObject(randomPower, 0);
            PowerUp.shape = new Ellipse2D.Float(0, 0, 15, 15);
            PowerUp.material = new Material(Diamond, Diamond, 1);
            PowerUp.scripts.add(new PowerMovement(PowerUp));
            PowerUp.scripts.add(new PowerUp(PowerUp, Player, aliens));
            GatorEngine.Create(PowerUp);
        }

        System.out.println(r.nextDouble());
    }

    static void LevelTwo() {
        GatorEngine.UpdateObjectList();
        GatorEngine.OBJECTLIST.clear();
        Input.UpdateInputs();

        GameObject Game = new GameObject(0, 0);
        Game.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 0.0F, 0.0F);
        Game.material = new Material("resources/Background.png");
        GatorEngine.Create(Game);
        Game.Scale(0.5f, 0.5f);

        GameObject firstHP = new GameObject(10, 460);
        firstHP.material = new Material("resources/Ship.png");
        firstHP.Scale(0.9f, 0.9f);
        GatorEngine.Create(firstHP);

        GameObject secondHP = new GameObject(45, 460);
        secondHP.material = new Material("resources/Ship.png");
        secondHP.Scale(0.9f, 0.9f);
        GatorEngine.Create(secondHP);

        GameObject thirdHP = new GameObject(80, 460);
        thirdHP.material = new Material("resources/Ship.png");
        thirdHP.Scale(0.9f, 0.9f);
        GatorEngine.Create(thirdHP);

        GameObject Player = new GameObject(238, 400);
        Player.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 30.0F, 50.0F);
        Player.material = new Material("resources/Ship.png");
        Player.scripts.add(new PlayerMovement(Player));
        Player.Scale(1.5f, 1.5f);
        GatorEngine.Create(Player);

        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < 6; j++) {
                GameObject Enemy = new GameObject(j * 50, i * 50);
                Enemy.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 25.0F, 25.0F);
                Enemy.transform.translate(75, -25);
                Enemy.material = new Material("resources/Enemy2.png");
                Enemy.Scale(1.5f, 1.5f);
                Enemy.scripts.add(new EnemyMovement(Enemy));
               //  Enemy.scripts.add(new EnemyAttack(Enemy, Player, firstHP, secondHP, thirdHP));
                //Enemy.scripts.add(new PlayerAttack(Enemy, Player));
                GatorEngine.Create(Enemy);
            }
        }
    }

    static void LevelThree() {
        GatorEngine.UpdateObjectList();
        GatorEngine.OBJECTLIST.clear();
        Input.UpdateInputs();

        GameObject Game = new GameObject(0, 0);
        Game.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 0.0F, 0.0F);
        Game.material = new Material("resources/Background.png");
        GatorEngine.Create(Game);
        Game.Scale(0.5f, 0.5f);

        GameObject firstHP = new GameObject(10, 460);
        firstHP.material = new Material("resources/Ship.png");
        firstHP.Scale(0.9f, 0.9f);
        GatorEngine.Create(firstHP);

        GameObject secondHP = new GameObject(45, 460);
        secondHP.material = new Material("resources/Ship.png");
        secondHP.Scale(0.9f, 0.9f);
        GatorEngine.Create(secondHP);

        GameObject thirdHP = new GameObject(80, 460);
        thirdHP.material = new Material("resources/Ship.png");
        thirdHP.Scale(0.9f, 0.9f);
        GatorEngine.Create(thirdHP);

        GameObject Player = new GameObject(238, 400);
        Player.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 30.0F, 50.0F);
        Player.material = new Material("resources/Ship.png");
        Player.scripts.add(new PlayerMovement(Player));
        Player.Scale(1.5f, 1.5f);
        GatorEngine.Create(Player);

        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < 6; j++) {
                GameObject Enemy = new GameObject(j * 50, i * 50);
                Enemy.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 25.0F, 25.0F);
                Enemy.transform.translate(75, -25);
                Enemy.material = new Material("resources/Enemy3.png");
                Enemy.Scale(1.5f, 1.5f);
                Enemy.scripts.add(new EnemyMovement(Enemy));
                //Enemy.scripts.add(new EnemyAttack(Enemy, Player, firstHP, secondHP, thirdHP));
             //   Enemy.scripts.add(new PlayerAttack(Enemy, Player));
                GatorEngine.Create(Enemy);
            }
        }
    }

    static void End() {
        GatorEngine.OBJECTLIST.clear();
        Color BrightGreen = new Color(0, 255, 0, 100);

        GameObject DeathScreen = new GameObject(0, 0);
        DeathScreen.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 0.0F, 0.0F);
        DeathScreen.material = new Material("resources/DeathScreen.png");
        GatorEngine.Create(DeathScreen);
        DeathScreen.Scale(0.5f, 0.5f);

        GameObject EndBox = new GameObject(0, 0);
        EndBox.shape = new java.awt.geom.Rectangle2D.Float(145.0F, 384.0F, 222.0F, 50.0F);
        EndBox.material = new Material(BrightGreen, BrightGreen, 2);
        EndBox.scripts.add(new MouseHoverEnd(EndBox));
        GatorEngine.Create(EndBox);
    }

    static class MouseHoverStart extends ScriptableBehavior {
        Color BrightGreen = new Color(0, 255, 0, 100);
        Color NoColor = new Color(0, 0, 0, 0);

        MouseHoverStart(GameObject g) {
            super(g);
        }

        @Override
        public void Start() {
        }

        @Override
        public void Update() {

            if (Input.MouseX > 202 && Input.MouseX < 300 && Input.MouseY > 313 && Input.MouseY < 364) {
                gameObject.material.setFill(BrightGreen);
                gameObject.material.setBorder(BrightGreen);
                if (Input.MouseClicked) {
                    GatorInvaders.LevelOne();
                    GatorEngine.Delete(gameObject);
                }
            } else
                gameObject.material.setFill(NoColor);
            gameObject.material.setBorder(NoColor);
        }
    }

    static class MouseHoverEnd extends ScriptableBehavior {
        Color BrightGreen = new Color(0, 255, 0, 100);
        Color NoColor = new Color(0, 0, 0, 0);

        MouseHoverEnd(GameObject g) {
            super(g);
        }

        @Override
        public void Start() {
        }

        @Override
        public void Update() {

            if (Input.MouseX > 144 && Input.MouseX < 366 && Input.MouseY > 383 && Input.MouseY < 433) {
                gameObject.material.setFill(BrightGreen);
                gameObject.material.setBorder(BrightGreen);
                if (Input.MouseClicked) {
                    GatorInvaders.Start();
                    GatorEngine.Delete(gameObject);
                }
            } else
                gameObject.material.setFill(NoColor);
            gameObject.material.setBorder(NoColor);
        }
    }

    static class PowerUp extends ScriptableBehavior {
        GameObject g2;
        ArrayList<GameObject> g3;
        static boolean isPowered;
        static long startTime;
        static long Power = 1000; // 5000 milliseconds (5 second)

        PowerUp(GameObject g, GameObject g2, ArrayList<GameObject> g3) {
            super(g);
            this.g2 = g2;
            this.g3 = g3;
        }

        @Override
        public void Start() {
            isPowered = false;
        }

        @Override
        public void Update() {

            if (gameObject.CollidesWith(g2)) {
                g2.material.setImg("resources/ShipUpgrade.png");
                isPowered = true;
                startTime = System.currentTimeMillis();
                g2.scripts.add(new PlayerAttack(g2, g3, 10));
                GatorEngine.Delete(gameObject);
            }

        }
    }

    static class PowerMovement extends ScriptableBehavior {
        private int dx = 1;
        private int dy = 0;
        private int oldDx = dx;
        private int count = 0;
        private int maxCount = 50;
        private float velocity = 2.5f;

        PowerMovement(GameObject g) {
            super(g);
        }

        @Override
        public void Start() {
        }

        @Override
        public void Update() {
            if (count > -1 && count < maxCount) {
                // Right
                if (dx == 1 && dy == 0) {
                    gameObject.Translate(velocity * dx, 0);
                    count++;
                }

                // Down
                else if (dx == 0 && dy == 1) {
                    gameObject.Translate(0, velocity * dy);
                    count++;
                }

                // Left
                else if (dx == -1 && dy == 0) {
                    gameObject.Translate(velocity * dx, 0);
                    count++;
                }

            } else if (count >= maxCount) {
                // Right -> Down
                if (dx == 1 && dy == 0) {
                    oldDx = dx;
                    dx = 0;
                    dy = 1;
                    count = maxCount / 2;
                }
                // Down to Right or Left
                else if (dy == 1) {
                    dy = 0;
                    if (oldDx == 1) {
                        dx = -1;
                    } else if (oldDx == -1) {
                        dx = 1;
                    }
                    count = 0;
                } else if (dx == -1 && dy == 0) {
                    oldDx = dx;
                    dx = 0;
                    dy = 1;
                    count = maxCount / 2;
                }
            }
        }
    }

    static class PlayerMovement extends ScriptableBehavior {
        PlayerMovement(GameObject g) {
            super(g);
        }

        @Override
        public void Start() {

        }

        @Override
        public void Update() {
            // Move right (a) or move left (d) within the bounds of the window.
            if (Input.GetKeyDown('a'))
                if (gameObject.transform.getTranslateX() > 0) {
                    gameObject.Translate(-1.5f, 0);
                }
            if (Input.GetKeyDown('d'))
                if (gameObject.transform.getTranslateX() < 455) {
                    gameObject.Translate(1.5f, 0);
                }
        }
    }

    static class PlayerAttack extends ScriptableBehavior {
        int Delay;
        ArrayList<GameObject> g2;
        int Reload;

        PlayerAttack(GameObject g, ArrayList<GameObject> g2, int Reload) {
            super(g);
            this.g2 = g2;
            this.Reload = Reload;
        }

        @Override
        public void Start() {
            Delay = 0;
        }

        @Override
        public void Update() {

            //create a new game object (g)
            //set it up, with material, shape, img...
            //assign a bullet script that moves it
            //GatorEngine.Create(g)
            Delay++;
            if (Input.GetKeyDown(' ')) {
                if (Delay > Reload) {
                    GameObject Bullet = new GameObject();
                    Bullet.shape = new Ellipse2D.Float((float) gameObject.transform.getTranslateX() + 18, (float) gameObject.transform.getTranslateY(), 10, 10);
                    Bullet.material = new Material(Color.CYAN, Color.CYAN, 1);
                    Bullet.scripts.add(new PlayerShoot(Bullet));
                    Bullet.scripts.add(new Destroy(Bullet, g2));
                    GatorEngine.Create(Bullet);
                    Delay = 0;
                }
            }
        }
    }

//    static class DamageEnemies2 extends ScriptableBehavior {
//        GameObject g2, g3, g4;
//        static int enemyCount = 18;
//        static int level = 1;
//        static boolean hasCollided = false;
//        int Delay;
//
//        DamageEnemies2(GameObject g, GameObject g2, GameObject g3, GameObject g4) {
//            super(g);
//            this.g2 = g2;
//            this.g3 = g3;
//            this.g4 = g4;
//        }
//
//        @Override
//        public void Start() {
//            enemyCount = 18;
//            level = 1;
//        }
//
//        @Override
//        public void Update() {
////            if (g2.CollidesWith(gameObject)) {
////                GatorEngine.Delete(g2);
////                g2.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 0F, 0F);
////                enemyCount--;
////                hasCollided = true;
////            }
//            if (g2.)
//
//            else if (g3.CollidesWith(gameObject)) {
//                GatorEngine.Delete(g3);
//                g3.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 0F, 0F);
//                enemyCount--;
//                hasCollided = true;
//            }
//
//            else if (g4.CollidesWith(gameObject)) {
//                GatorEngine.Delete(g4);
//                g4.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 0F, 0F);
//                enemyCount--;
//                hasCollided = true;
//            }
//
//            if (hasCollided) {
//                hasCollided = false;
//                GatorEngine.Delete(gameObject);
//            }
//
//            if (enemyCount <= 0) {
//                Delay++;
//                if (Delay > 50) {
//                    if (level == 1) {
//                        enemyCount = 18;
//                        Delay = 0;
//                        level = 2;
//                        GatorInvaders.LevelTwo();
//                    } else if (level == 2) {
//                        enemyCount = 18;
//                        Delay = 0;
//                        level = 3;
//                        GatorInvaders.LevelThree();
//                    } else {
//                        enemyCount = 18;
//                        Delay = 0;
//                        level = 1;
//                        GatorInvaders.LevelOne();
//                    }
//                }
//            }
//            System.out.println("Level: " + level + " EnemyCount = " + enemyCount);
//        }
//    }

    static class Destroy extends ScriptableBehavior {
        ArrayList<GameObject> g2;
        int Delay;

        Destroy(GameObject g, ArrayList<GameObject> g2) {
            super(g);
            this.g2 = g2;
        }

        @Override
        public void Start() {
            Delay = 0;
        }

        @Override
        public void Update() {

            for (GameObject enemy : g2)
            if (gameObject.CollidesWith(enemy)) {
                GatorEngine.Delete(gameObject);
                enemy.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 0, 0);
                GatorEngine.Delete(enemy);
                g2.remove(enemy);
            }

            if (g2.size() == 0) {
                Delay++;
                if (Delay > 30) {
                    GatorInvaders.LevelOne();
                }
            }
        }
    }

    static class PlayerShoot extends ScriptableBehavior {
        PlayerShoot(GameObject g) {
            super(g);
        }

        @Override
        public void Start() {

        }

        @Override
        public void Update() {
            gameObject.Translate(0, -10f);

        }
    }

    static class EnemyMovement extends ScriptableBehavior {
        // dx, dy = (1, 0) right<----
        //          (0, 1) down    |
        //          (-1, 0) left    |
        //          (0, 1) down    |
        //          (1, 0) right ----
        // Objective: Cycle.

        private int dx = 1;
        private int dy = 0;
        private int oldDx = dx;
        private int count = 0;
        private int maxCount = 75;
        private float velocity = 0.40f;

        EnemyMovement(GameObject g) {
            super(g);
        }

        @Override
        public void Start() {
        }

        @Override
        public void Update() {
            if (count > -1 && count < maxCount) {
                // Right
                if (dx == 1 && dy == 0) {
                    gameObject.Translate(velocity * dx, 0);
                    count++;
                }

                // Down
                else if (dx == 0 && dy == 1) {
                    gameObject.Translate(0, velocity * dy);
                    count++;
                }

                // Left
                else if (dx == -1 && dy == 0) {
                    gameObject.Translate(velocity * dx, 0);
                    count++;
                }

            } else if (count >= maxCount) {
                // Right -> Down
                if (dx == 1 && dy == 0) {
                    oldDx = dx;
                    dx = 0;
                    dy = 1;
                    count = maxCount / 2;
                }
                // Down to Right or Left
                else if (dy == 1) {
                    dy = 0;
                    if (oldDx == 1) {
                        dx = -1;
                    } else if (oldDx == -1) {
                        dx = 1;
                    }
                    count = 0;
                } else if (dx == -1 && dy == 0) {
                    oldDx = dx;
                    dx = 0;
                    dy = 1;
                    count = maxCount / 2;
                }
            }

            if (gameObject.transform.getTranslateY() > 350) {
                GatorInvaders.End();
                GatorEngine.Delete(gameObject);
            }

        }
    }

    static class EnemyAttack extends ScriptableBehavior {
        GameObject g2;
        ArrayList<GameObject> g3;

        EnemyAttack(GameObject g, GameObject g2, ArrayList<GameObject> g3) {
            super(g);
            this.g2 = g2;
            this.g3 = g3;
        }

        @Override
        public void Start() {

        }

        @Override
        public void Update() {
            Random r = new Random();

            if (r.nextDouble() < 0.001) {
                GameObject EnemyBullet = new GameObject();
                EnemyBullet.shape = new Ellipse2D.Float((float) gameObject.transform.getTranslateX() + 18, (float) gameObject.transform.getTranslateY(), 10, 10);
                EnemyBullet.material = new Material(Color.RED, Color.RED, 1);
                EnemyBullet.scripts.add(new EnemyShoot(EnemyBullet));
                EnemyBullet.scripts.add(new PlayerDamaged(EnemyBullet, g2, g3));
                GatorEngine.Create(EnemyBullet);
            }
        }
    }

    static class PlayerDamaged extends ScriptableBehavior {
        GameObject g2;
        ArrayList<GameObject> g3;
        static boolean isInvincible;
        static long startTime;
        static long Invincibility = 1000; // 1000 milliseconds (1 second)

        PlayerDamaged(GameObject g, GameObject g2, ArrayList<GameObject> g3) {
            super(g);
            this.g2 = g2;
            this.g3 = g3;

        }

        @Override
        public void Start() {
            isInvincible = false;
        }
        @Override
        public void Update() {
            for (GameObject hp : g3)

                if (gameObject.CollidesWith(g2) && !isInvincible) {
                    g2.material.setImg("resources/ShipHit.png");
                    isInvincible = true;
                    startTime = System.currentTimeMillis();
                    GatorEngine.Delete(gameObject);
                    GatorEngine.Delete(hp);
                    g3.remove(hp);
                }

                if (isInvincible){
                    long currentTime = System.currentTimeMillis();
                    long elapsedTime = currentTime - startTime;

                    if (elapsedTime > Invincibility) {
                        g2.material.setImg("resources/Ship.png");
                        isInvincible = false;
                        startTime = 0;
                    }
                }

                if (g3.size() == 0) {
                    GatorInvaders.End();
                }
        }
    }

    static class EnemyShoot extends ScriptableBehavior {
            EnemyShoot(GameObject g) {
                super(g);
            }

            @Override
            public void Start() {

            }

            @Override
            public void Update() {
                gameObject.Translate(0, 10f);

            }
        }


}