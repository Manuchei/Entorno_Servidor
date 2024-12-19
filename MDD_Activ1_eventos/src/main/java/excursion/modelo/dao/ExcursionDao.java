package excursion.modelo.dao;

import java.util.List;

import excursion.modelo.javabean.Excursion;

public interface ExcursionDao {

	Excursion findbyId(long idExcursion);

	int insertOne(Excursion excursion);

	int updateOne(Excursion excursion);

	int deleteOne(Excursion excursion);

	int deleteOne(long idExcursion);

	List<Excursion> findAll();

	List<Excursion> findbyDescripcionContaining(String cadena);

}
