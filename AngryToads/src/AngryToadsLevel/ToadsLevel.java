/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryToadsLevel;

import AngryToadsApplication.AngryToadsArea;
import AngryToadsCharacters.AngryToadsGround;
import AngryToadsCharacters.AngryToadsModel;
import AngryToadsCharacters.AngryToadsObstacles;
import AngryToadsCharacters.AngryToadsPig;
import AngryToadsCharacters.AngryToadsSling;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.*;


public class ToadsLevel extends AngryToadsArea {

    public ToadsLevel() {
        super();
    }

    @Override
    public void initStage() {

        AngryToadsGround gu = new AngryToadsGround();
        AngryToadsModel bu = new AngryToadsModel();
        AngryToadsObstacles ou = new AngryToadsObstacles();
        AngryToadsPig pig = new AngryToadsPig();

        ground = gu.createGround(sworld);

        Vec2 pos = new Vec2();
        for (int i = 0; i < 2; i++) {
            pos.set(2 + i / 2, 0.5f);
            this.birdlist.add(bu.createBirds(sworld, 1, pos));

            pos.set(40 + 2 * i, 1f);
            float dheight = 1.2f;
            for (int j = 0; j < 1; j++) {
                
                if (j == 0) {
                    
                    dheight *= 0;
                }
                this.oblist.add(ou.createObstacles(sworld, 4, pos.set(pos.x, pos.y + dheight), (float) Math.PI / 2));

                dheight = 1.2f;
                this.oblist.add(ou.createObstacles(sworld, 4, pos.set(pos.x, pos.y + dheight), 0));

            }

        }




        birdbullets = 0;
        pos.set(5f, 5.4f);
        WeldJointDef wd = new WeldJointDef();
        slingAnchor.set(pos);
        wd.bodyA = ground;
        wd.bodyB = birdlist.get(0);
        wd.localAnchorA.set(pos.sub(ground.getPosition()));
        attachDef = wd;
        attach = (WeldJoint) sworld.createJoint(wd);
        birdlist.get(0).setTransform(pos, 0);



    }
}
