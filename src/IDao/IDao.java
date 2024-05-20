package IDao;

import java.util.List;

public interface IDao <T>{
	
	List<T> findAll ( );
	
}