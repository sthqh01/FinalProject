/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.view;

import edu.moravian.entity.Entity;
import edu.moravian.entity.wave.EntityManager;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Renderable;

/**
 *
 * @author danielhuynh
 */
public class SpriteRendererManager 
{
    private ArrayList<SpriteRenderer> spriteRendererList;
    private Renderable renderable;
    private EntityManager entityManager;
    public SpriteRendererManager(Renderable renderable, EntityManager agentManager)
    {
        this.renderable = renderable;
        this.entityManager = agentManager;
        this.spriteRendererList = new ArrayList<>();
    }
    
    public void update()
    {
        this.spriteRendererList.clear();
        for(Entity entity : entityManager.getEntity())
            this.spriteRendererList.add(new SpriteRenderer(this.renderable, entity));
        for(SpriteRenderer spriteRenderer : this.spriteRendererList)
            spriteRenderer.update();
    }
    
    public void render(Graphics grphcs)
    {
        for(SpriteRenderer spriteRenderer : this.spriteRendererList)
            spriteRenderer.render(grphcs);
    }
}
