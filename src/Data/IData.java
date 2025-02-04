package Data;

import java.util.ArrayList;

public interface IData<IEntity> {
    public ArrayList<IEntity> getAll();
    public IEntity getById(int id);
    public int add(IEntity entity);
    public int update(IEntity entity);
    public int delete(int id);
}
