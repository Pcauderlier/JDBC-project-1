package Logic;

import java.util.ArrayList;

public interface IService<IEntity> {
    public ArrayList<IEntity> getAll();
    public IEntity getById(int id);
    public IEntity add(IEntity entity);
    public IEntity update(IEntity entity);
    public boolean delete(int id);

}
