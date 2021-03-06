
package org.bukkit.craftbukkit;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.EntityPlayerMP;
import net.minecraft.server.Packet3Chat;
import org.bukkit.Location;
import org.bukkit.Player;

public class CraftPlayer extends CraftHumanEntity implements Player {
    private EntityPlayerMP entity;

    public CraftPlayer(CraftServer server, EntityPlayerMP entity) {
        super(server, entity);
        this.entity = entity;
    }

    public boolean isOnline() {
        return server.getHandle().g(getName());
    }

    @Override
    public EntityPlayerMP getHandle() {
        return entity;
    }

    public void setHandle(final EntityPlayerMP entity) {
        super.setHandle((EntityPlayer)entity);
        this.entity = entity;
    }

    public void sendMessage(String message) {
        entity.a.b(new Packet3Chat(message));
    }

    @Override
    public void teleportTo(Location location) {
        entity.a.a(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    @Override
    public String toString() {
        return "CraftPlayer{" + "name=" + getName() + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CraftPlayer other = (CraftPlayer) obj;
        if ((this.getName() == null) ? (other.getName() != null) : !this.getName().equals(other.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.getName() != null ? this.getName().hashCode() : 0);
        return hash;
    }
}
