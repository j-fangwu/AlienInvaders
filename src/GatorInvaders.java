import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class GatorInvaders {
    static ArrayList<Alien> aliens = new ArrayList<>();
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
        updateLevel();
        int currentLevel = 1;

        GatorInvaders.Background();

        setHP();

        GameObject Player = new GameObject(238, 400);
        Player.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 30.0F, 30.0F);
        Player.material = new Material("resources/Ship.png");
        Player.scripts.add(new PlayerMovement(Player));
        Player.scripts.add(new PlayerAttack(Player, aliens, currentLevel));
        Player.Scale(1.5f, 1.5f);
        GatorEngine.Create(Player);

        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < 6; j++) {
                Alien Enemy = new Alien(j * 50, i * 50, 1);
                Enemy.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 27.5F, 27.5F);
                Enemy.transform.translate(75, -25);
                Enemy.material = new Material("resources/Enemy1.png");
                Enemy.Scale(1.5f, 1.5f);
                Enemy.scripts.add(new EnemyMovement(Enemy, Player));
                Enemy.scripts.add(new EnemyAttack(Enemy, Player, health));
                aliens.add(Enemy);
                GatorEngine.Create(Enemy);
            }
        }
    }

    static void LevelTwo() {
        updateLevel();
        int currentLevel = 2;

        GatorInvaders.Background();

        setHP();

        GameObject Player = new GameObject(238, 400);
        Player.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 30.0F, 30.0F);
        Player.material = new Material("resources/Ship.png");
        Player.scripts.add(new PlayerMovement(Player));
        Player.scripts.add(new PlayerAttack(Player, aliens, currentLevel));
        Player.Scale(1.5f, 1.5f);
        GatorEngine.Create(Player);

        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < 6; j++) {
                Alien Enemy;
                if (aliens.size() > 0 & aliens.size() < 5 || aliens.size() > 7 && aliens.size() < 10) {
                    Enemy = new Alien(j * 50, i * 50, 2);
                    Enemy.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 27.5F, 27.5F);
                    Enemy.transform.translate(75, -25);
                    Enemy.material = new Material("resources/Enemy2.png");
                } else {
                    Enemy = new Alien(j * 50, i * 50, 1);
                    Enemy.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 27.5F, 27.5F);
                    Enemy.transform.translate(75, -25);
                    Enemy.material = new Material("resources/Enemy1.png");
                }
                Enemy.Scale(1.5f, 1.5f);
                Enemy.scripts.add(new EnemyMovement(Enemy, Player));
                Enemy.scripts.add(new EnemyAttack(Enemy, Player, health));
                aliens.add(Enemy);
                GatorEngine.Create(Enemy);
            }
        }
    }

    static void LevelThree() {
        updateLevel();
        int currentLevel = 3;

        GatorInvaders.Background();

        setHP();

        GameObject Player = new GameObject(238, 400);
        Player.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 30.0F, 30.0F);
        Player.material = new Material("resources/Ship.png");
        Player.scripts.add(new PlayerMovement(Player));
        Player.scripts.add(new PlayerAttack(Player, aliens, currentLevel));
        Player.Scale(1.5f, 1.5f);
        GatorEngine.Create(Player);

        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < 6; j++) {
                if ((aliens.size() > 0 && aliens.size() < 5) ||(aliens.size() > 7 && aliens.size() < 10)) {
                    Alien Enemy = new Alien(j * 50, i * 50, 3);
                    Enemy.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 27.5F, 27.5F);
                    Enemy.transform.translate(75, -25);
                    Enemy.material = new Material("resources/Enemy3.png");
                    Enemy.Scale(1.5f, 1.5f);
                    Enemy.scripts.add(new EnemyMovement(Enemy, Player));
                    Enemy.scripts.add(new EnemyAttack(Enemy, Player, health));
                    aliens.add(Enemy);
                    GatorEngine.Create(Enemy);
                } else if (aliens.size() == 0 || aliens.size() <= 7 || aliens.size() < 12 || aliens.size() > 12 && aliens.size() < 17) {
                    Alien Enemy = new Alien(j * 50, i * 50, 2);
                    Enemy.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 27.5F, 27.5F);
                    Enemy.transform.translate(75, -25);
                    Enemy.material = new Material("resources/Enemy2.png");
                    Enemy.Scale(1.5f, 1.5f);
                    Enemy.scripts.add(new EnemyMovement(Enemy, Player));
                    Enemy.scripts.add(new EnemyAttack(Enemy, Player, health));
                    aliens.add(Enemy);
                    GatorEngine.Create(Enemy);
                } else {
                    Alien Enemy = new Alien(j * 50, i * 50, 1);
                    Enemy.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 27.5F, 27.5F);
                    Enemy.transform.translate(75, -25);
                    Enemy.material = new Material("resources/Enemy1.png");
                    Enemy.Scale(1.5f, 1.5f);
                    Enemy.scripts.add(new EnemyMovement(Enemy, Player));
                    Enemy.scripts.add(new EnemyAttack(Enemy, Player, health));
                    aliens.add(Enemy);
                    GatorEngine.Create(Enemy);
                }
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

    static class PowerMovement extends ScriptableBehavior {
        // dx, dy = (0, 1) down<----
        //          (1, 0) right    |
        //          (0, 1) down    |
        //          (-1, 0) left    |
        //          (0, 1) down ----
        // Objective: Cycle.

        private int dx = 0;
        private int dy = 1;
        private int oldDx = 1;
        private int count = 0;
        private int maxCount = 50;
        private float velocity = 2f;

        PowerMovement(GameObject g) {
            super(g);
        }

        @Override
        public void Start() {
        }

        @Override
        public void Update() {
            if (count > -1 && count < maxCount) {
                // Down
                if (dx == 0 && dy == 1) {
                    gameObject.Translate(0, velocity * dy);
                    count++;
                }

                // Right
                else if (dx == 1 && dy == 0) {
                    gameObject.Translate(velocity * dx, 0);
                    count++;
                }

                // Left
                else if (dx == -1 && dy == 0) {
                    gameObject.Translate(velocity * dx, 0);
                    count++;
                }

            } else if (count >= maxCount) {
                // Down to Right or Left
                if (dy == 1) {
                    dy = 0;
                    if (oldDx == 1) {
                        dx = -1;
                    } else if (oldDx == -1) {
                        dx = 1;
                    }
                    count = 0;
                }
                // Right
                else if (dx == 1 && dy == 0) {
                    oldDx = dx;
                    dx = 0;
                    dy = 1;
                    count = maxCount / 2;
                }

                // Left
                else if (dx == -1 && dy == 0) {
                    oldDx = dx;
                    dx = 0;
                    dy = 1;
                    count = maxCount / 2;
                }
            }

            if (gameObject.transform.getTranslateY() > 420) {
                GatorEngine.Delete(gameObject);
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

    static class PlayerMovementBuffed extends ScriptableBehavior {
        PlayerMovementBuffed(GameObject g) {
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
                    gameObject.Translate(-0.5f, 0);
                }
            if (Input.GetKeyDown('d'))
                if (gameObject.transform.getTranslateX() < 455) {
                    gameObject.Translate(0.5f, 0);
                }
        }
    }

    static class PlayerAttack extends ScriptableBehavior {
        int Delay;
        ArrayList<Alien> g2;
        int currentLevel;

        PlayerAttack(GameObject g, ArrayList<Alien> g2, int currentLevel) {
            super(g);
            this.g2 = g2;
            this.currentLevel = currentLevel;
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
                if (Delay > 30) {
                    GameObject Bullet = new GameObject();
                    Bullet.shape = new Ellipse2D.Float((float) gameObject.transform.getTranslateX() + 18, (float) gameObject.transform.getTranslateY(), 10, 10);
                    Bullet.material = new Material(Color.CYAN, Color.CYAN, 1);
                    Bullet.scripts.add(new PlayerShoot(Bullet));
                    Bullet.scripts.add(new Destroy(Bullet, g2, currentLevel));
                    GatorEngine.Create(Bullet);
                    Delay = 0;
                }
            }
        }
    }

    static class Destroy extends ScriptableBehavior {
        ArrayList<Alien> g2;
        int Delay;
        int currentLevel;

        Destroy(GameObject g, ArrayList<Alien> g2, int currentLevel) {
            super(g);
            this.g2 = g2;
            this.currentLevel = currentLevel;
        }

        @Override
        public void Start() {
            Delay = 0;
        }

        @Override
        public void Update() {

            for (Alien enemy : aliens)
            if (gameObject.CollidesWith(enemy)) {
                if (enemy.level == 1) {
                    GatorEngine.Delete(gameObject);
                    enemy.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 0, 0);
                    GatorEngine.Delete(enemy);
                    g2.remove(enemy);
                } else if (enemy.level == 2) {
                    enemy.material.setImg("resources/Enemy1.png");
                    GatorEngine.Delete(gameObject);
                    enemy.level--;
                }else if (enemy.level == 3) {
                    enemy.material.setImg("resources/Enemy2.png");
                    GatorEngine.Delete(gameObject);
                    enemy.level--;
                }
            }

            if (g2.size() == 0) {
                Delay++;
                if (Delay > 30) {
                    if (currentLevel == 1) {
                        GatorInvaders.LevelTwo();
                    } else if (currentLevel == 2) {
                        GatorInvaders.LevelThree();
                    } else if (currentLevel == 3) {
                        GatorInvaders.LevelOne();
                    }
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

            if (gameObject.transform.getTranslateY() < -400) {
                GatorEngine.Delete(gameObject);
            }

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
        GameObject g2;

        EnemyMovement(GameObject g, GameObject g2) {
            super(g);
            this.g2 = g2;
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

            if (gameObject.transform.getTranslateY() >= g2.transform.getTranslateY() - 30) {
                GatorInvaders.End();
                GatorEngine.Delete(gameObject);
            }
        }
    }

    static class EnemyAttack extends ScriptableBehavior {
        GameObject g2;
        ArrayList<GameObject> g3;
        Color Diamond = new Color(115, 250, 205);

        EnemyAttack(Alien g, GameObject g2, ArrayList<GameObject> g3) {
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

            for (Alien alien : aliens)
            if (alien.level == 1) {
                if (r.nextDouble() < 0.00005) {
                    EnemyBullet();
                }

            } else if (alien.level == 2) {
                if (r.nextDouble() < 0.0001) {
                    EnemyBullet();
                }
            } else if (alien.level == 3) {
                if (r.nextDouble() < 0.0005) {
                    EnemyBullet();
                }
            }

            Random r2 = new Random();

            if (r2.nextDouble() < 0.00025) {
                GameObject PowerUp = new GameObject();
                PowerUp.shape = new Ellipse2D.Float((float) gameObject.transform.getTranslateX() + 18, (float) gameObject.transform.getTranslateY(), 15, 15);
                PowerUp.material = new Material(Color.white, Color.white, 1);
                PowerUp.material.setBorder(Diamond);
                PowerUp.scripts.add(new PowerMovement(PowerUp));
                PowerUp.scripts.add(new PlayerBuffed(PowerUp, g2, g3));
                GatorEngine.Create(PowerUp);
            }


        }

         void EnemyBullet() {
            GameObject EnemyBullet = new GameObject();
            EnemyBullet.shape = new Ellipse2D.Float((float) gameObject.transform.getTranslateX() + 18, (float) gameObject.transform.getTranslateY(), 10, 10);
            EnemyBullet.material = new Material(Color.RED, Color.RED, 1);
            EnemyBullet.scripts.add(new EnemyShoot(EnemyBullet));
            EnemyBullet.scripts.add(new PlayerDamaged(EnemyBullet, g2, g3));
            GatorEngine.Create(EnemyBullet);
        }
    }

    static class PlayerDamaged extends ScriptableBehavior {
        GameObject g2;
        ArrayList<GameObject> g3;
        static boolean isInvincible;
        static long startTime;
        static long Invincibility = 1250; // 1250 milliseconds (1.25 second)

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

    static class PlayerBuffed extends ScriptableBehavior {
        GameObject g2;
        ArrayList<GameObject> g3;
        static boolean isInvincible;
        static long startTime;// 1000 milliseconds (1 second)

        PlayerBuffed(GameObject g, GameObject g2, ArrayList<GameObject> g3) {
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

                if (gameObject.CollidesWith(g2)) {
                    g2.material.setImg("resources/ShipUpgrade.png");
                    startTime = System.currentTimeMillis();
                    GatorEngine.Delete(gameObject);
                    g2.scripts.add(new PlayerMovementBuffed(g2));
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
                gameObject.Translate(0, 7.5f);

                if (gameObject.transform.getTranslateY() > 420) {
                    GatorEngine.Delete(gameObject);
                }

            }
        }

     static class Alien extends GameObject {
        int level;
        Alien(int x, int y, int level) {
            super(x, y);
            this.level = level;
        }
    }

    static void Background() {
        GameObject Background = new GameObject(0, 0);
        Background.shape = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 0.0F, 0.0F);
        Background.material = new Material("resources/Background.png");
        GatorEngine.Create(Background);
        Background.Scale(0.5f, 0.5f);
    }

    static void updateLevel() {
        GatorEngine.UpdateObjectList();
        GatorEngine.OBJECTLIST.clear();
        Input.UpdateInputs();
        aliens.clear();
        health.clear();
    }

    static void setHP() {
        for (int i = 2; i >= 0; i--) {
            GameObject healthPoint = new GameObject((35 * i) + 10 , 460);
            healthPoint.material = new Material("resources/Ship.png");
            healthPoint.Scale(0.9f, 0.9f);
            GatorEngine.Create(healthPoint);
            health.add(healthPoint);
        }
    }

}