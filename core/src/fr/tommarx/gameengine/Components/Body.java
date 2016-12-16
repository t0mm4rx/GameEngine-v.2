package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Util.Math;

public class Body extends Component {


    com.badlogic.gdx.physics.box2d.Body body;

    public Body(AbstractGameObject go, BodyDef.BodyType type, FixtureDef def) {
        super(go);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.position.set(go.getTransform().getPosition().x + offsetX, go.getTransform().getPosition().y + offsetY);
        bodyDef.angle = Math.DegreeToRadian(go.getTransform().getRotation());
        body = Game.getCurrentScreen().world.createBody(bodyDef);
        body.createFixture(def);
    }

    public void render() {

    }

    public void renderInHUD() {

    }

    public void update() {
        getGameObject().getTransform().setPosition(new Vector2(body.getPosition().x + offsetX, body.getPosition().y + offsetY));
        getGameObject().getTransform().setRotation(Math.RadianToDegree(body.getAngle()));
    }

    public void dispose() {
        body.getWorld().destroyBody(body);
        body.setUserData(null);
        body = null;
    }

    public com.badlogic.gdx.physics.box2d.Body getBody() {
        return body;
    }
}
