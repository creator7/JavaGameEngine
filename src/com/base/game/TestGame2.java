package com.base.game;

import java.util.HashSet;
import java.util.Set;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import com.base.engine.core.Game;
import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;

public class TestGame2 extends Game
{
	private static DynamicsWorld dynamicsWorld;
	private static Set<RigidBody> balls = new HashSet<>();
	private static RigidBody controllBall;
//	private static boolean applyForce = false;
//	private static boolean createNewShape = false;
//	private static boolean resetControlBall = false;
	private static Sphere sphere = new Sphere();
	
	private static void setUpPhysics(){
		BroadphaseInterface broadphase = new DbvtBroadphase();
		CollisionConfiguration configuration = new DefaultCollisionConfiguration();
		CollisionDispatcher dispatcher = new CollisionDispatcher(configuration);
		ConstraintSolver solver = new SequentialImpulseConstraintSolver();
		dynamicsWorld = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, configuration);
		dynamicsWorld.setGravity(new Vector3f(0, -10, 0));
		CollisionShape groundShape = new StaticPlaneShape(new Vector3f(0, 1, 0), 0.25f);
		CollisionShape ballShape = new SphereShape(3.0f);
		MotionState groundMotionState = new DefaultMotionState(new Transform(
				new Matrix4f(new Quat4f(0, 0, 0, 1), new Vector3f(0, 0, 0), 1.0f)));
		RigidBodyConstructionInfo groundConstructionInfo = new RigidBodyConstructionInfo(0, groundMotionState, groundShape, new Vector3f(0, 0, 0));
		groundConstructionInfo.restitution = 0.25f;
		RigidBody groundRigidBody = new RigidBody(groundConstructionInfo);
		dynamicsWorld.addRigidBody(groundRigidBody);
		MotionState ballMotionState = new DefaultMotionState(new Transform(
				new Matrix4f(new Quat4f(0, 0, 0, 1), new Vector3f(0, 35, 0), 1.0f)));
		Vector3f ballInertia = new Vector3f(0, 0, 0);
		ballShape.calculateLocalInertia(2.5f, ballInertia);
		RigidBodyConstructionInfo ballConstructionInfo = new RigidBodyConstructionInfo(2.5f, ballMotionState, ballShape, ballInertia);
		ballConstructionInfo.restitution = 0.5f;
		ballConstructionInfo.angularDamping = 0.95f;
		controllBall = new RigidBody(ballConstructionInfo);
		controllBall.setActivationState(CollisionObject.DISABLE_DEACTIVATION);
		balls.add(controllBall);
		dynamicsWorld.addRigidBody(controllBall);
	
	}
	public void init(){
		setUpPhysics();
	}
	
	public static void render(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		for(RigidBody ball: balls){
			glPushMatrix();
			Vector3f ballPosition = ball.getWorldTransform(new Transform()).origin;
			glTranslatef(ballPosition.x, ballPosition.y, ballPosition.z);
			sphere.setDrawStyle(GLU.GLU_SILHOUETTE);
			if(ball.equals(controllBall))
				glColor4f(0, 1, 0, 1);
			
			sphere.draw(3.0f, 30, 30);
			glPopMatrix();
		}
		
		glBegin(GL_QUADS);
		glColor4f(0.5f, 0.5f, 0.5f, 1);
		glEnd();
		
	}
}
