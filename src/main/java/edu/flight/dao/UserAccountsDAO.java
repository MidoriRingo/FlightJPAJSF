package edu.flight.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import edu.flight.entity.Roles;
import edu.flight.entity.UserAccounts;
import edu.flight.transport.UserAccountsWeb;

@Repository
public class UserAccountsDAO extends GenericDAO<UserAccounts> {
	public Roles getRole(String role) {
		return em
				.createQuery(
						"select role from Roles role where role.name = ?1",
						Roles.class).setParameter(1, role).getSingleResult();
	}

	public void updateUser(UserAccountsWeb user) {
		Roles role = getRole(user.getRole());
		em.createQuery(
				"update UserAccounts user set user.login = ?1, user.mail = ?2, user.role = ?3 where user.userID = ?4")
				.setParameter(1, user.getLogin())
				.setParameter(2, user.getMail()).setParameter(3, role)
				.setParameter(4, user.getCode()).executeUpdate();
	}

	public UserAccountsWeb findUser(String login) {
		UserAccountsWeb user = null;
		try {
			user = em
					.createQuery(
							"select new edu.flight.transport.UserAccountsWeb(user.login, user.mail, role.name) FROM UserAccounts u, Roles role WHERE u.login = ?1 and u.role.code = role.code",
							UserAccountsWeb.class).setParameter(1, login)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return user;
	}

	public List<UserAccountsWeb> findAll() {
		List<UserAccountsWeb> list = em
				.createQuery(
						"select new edu.flight.transport.UserAccountsWeb(user.userID, user.login, user.mail, role.name) from UserAccounts user, Roles role where user.role.code = role.code",
						UserAccountsWeb.class).getResultList();
		return list;

	}

	public int getRoleLogin(String name, String password) {
		int role = em
				.createQuery(
						"select user.role.code from UserAccounts user where user.login = ?1 and user.password = ?2",
						Integer.class).setParameter(1, name)
				.setParameter(2, password).getSingleResult();
		return role;
	}
}
