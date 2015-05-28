package cn.ourpass.zxdata.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import cn.ourpass.zxdata.dbhelp.DbService;
import cn.ourpass.zxdata.exception.TableNotFoundException;
import cn.ourpass.zxdata.helpkits.EntityMap;
import cn.ourpass.zxdata.helpkits.EntityMapHelps;
import cn.ourpass.zxdata.helpkits.PreparedStatementBuild;
import cn.ourpass.zxdata.helpkits.SqlCreateUtils;

@Repository
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseDao<E extends Serializable, PK extends Serializable> {
	@Autowired
	private DbService dbService;
	private final static Logger log = Logger.getLogger(BaseDao.class);
	// 实体类类型(由构造方法自动赋值)
    private Class<E> entityClass;

    /**
     * 获得泛型类
     */
	public BaseDao() {
		log.info("---------BaseDao--------------");
		this.entityClass = null;
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) type).getActualTypeArguments();
			this.entityClass = (Class<E>) p[0];
		}
	}

	/**
	 * 添加实体，返回id
	 * 
	 * @param e
	 * @return
	 */
	public int add(E e) {
		int returnId = 0;
		ResultSet rs = null;
		Connection conn = dbService.getConnection();
		try {
			EntityMap<E, PK> em = new EntityMap(e);
			String sql = SqlCreateUtils.addSqlMake(em);
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			PreparedStatementBuild.buildAddStatement(ps, em);
			int executeUpdateReturn = ps.executeUpdate();
			conn.commit();
			rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
				returnId = rs.getInt(1);
			}
			log.info(sql + "  keyValue:" + returnId + " executeUpdateReturn:"
					+ executeUpdateReturn);
		} catch (SQLException e1) {
			try {
				conn.rollback();
			} catch (SQLException e2) {
				log.error(e2);
			}
			log.error(e1);
		} catch (TableNotFoundException e1) {
			log.error(e1);
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					log.error(e1);
				}
			}
			dbService.closeConnection(conn);
		}
		return returnId;
	}

	/**
	 * 删除对象
	 * 
	 * @param e
	 */
	public void delete(E e) {
		Connection conn = dbService.getConnection();
		try {
			EntityMap<E, PK> em = new EntityMap(e);
			String sql = SqlCreateUtils.deleteSqlMake(em);
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			int executeUpdateReturn = ps.executeUpdate();
			log.info(sql + "  keyValue:" + em.getKeyValue()
					+ " executeUpdateReturn:" + executeUpdateReturn);
			conn.commit();
		} catch (TableNotFoundException e1) {
			log.error(e1);
		} catch (SQLException e1) {
			log.error(e1);
			try {
				conn.rollback();
			} catch (SQLException e2) {
				log.error(e2);
			}
		} finally {
			dbService.closeConnection(conn);
		}
	}

	public void update(E e) {
		Connection conn = dbService.getConnection();
		try {
			EntityMap<E, PK> em = new EntityMap(e);
			String sql = SqlCreateUtils.updateSqlMake(em);
			int lastAskIndex = em.getAttributesList().size();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			PreparedStatementBuild.buildUpdateStatement(ps, em);
			int executeUpdateReturn = ps.executeUpdate();
			log.info(sql + "  keyValue:" + em.getKeyValue()
					+ " executeUpdateReturn:" + executeUpdateReturn);
			conn.commit();
		} catch (TableNotFoundException e1) {
			log.error(e1);
		} catch (SQLException e1) {
			log.error(e1);
			try {
				conn.rollback();
			} catch (SQLException e2) {
				log.error(e2);
			}
		} finally {
			dbService.closeConnection(conn);
		}
	}

	public E findById(PK id) {
		E e = null;
		Connection conn = dbService.getConnection();
		ResultSet rs = null;
		try {
			e = entityClass.newInstance();
			EntityMap<E, PK> em = new EntityMap(e);
			em.buildKeyValue(id);
			String sql = SqlCreateUtils.findSqlMake(em);
			PreparedStatement ps = conn.prepareStatement(sql);
			PreparedStatementBuild.buildFindStatement(ps, em);
			rs = ps.executeQuery();
			List<E> list = new EntityMapHelps().buildEntityByEntityMap(rs, em);
			if(list != null && list.size() > 0) {
				e = list.get(0);
			}
			log.info(sql + "  keyValue:" + em.getKeyValue());
		} catch (TableNotFoundException e1) {
			log.error(e1);
		} catch (Exception e1) {
			log.error(e1);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e1) {
				log.error(e1);
			}
			dbService.closeConnection(conn);
		}
		return e;
	}
	
	protected DbService getDbService() {
		return dbService;
	}
}
