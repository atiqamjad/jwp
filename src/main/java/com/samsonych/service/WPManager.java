/**
 *
 */
package com.samsonych.service;

import java.io.File;

import is.ida.lib.service.exception.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.samsonych.model.wp.Post;
import com.samsonych.model.wp.PostType;
import com.samsonych.model.wp.User;
import com.samsonych.service.dba.BaseDBManagerImpl;
import com.samsonych.service.dba.DBManagerFactory;

/**
 * @author samsonych
 *
 */
public class WPManager {

	private static Logger LOG = Logger.getLogger(WPManager.class);

	private static BaseDBManagerImpl postManager;
	private static BaseDBManagerImpl userManager;

	public WPManager() {
		postManager = DBManagerFactory.getBaseDBManager(Post.class);
		userManager = DBManagerFactory.getBaseDBManager(User.class);
	}

	public Post saveOrUpdatePost(Post post) throws ServiceException {
		return (Post) postManager.saveOrUpdate(post);
	}

	public static void main(String... args) {
		DBManagerFactory
				.setApplicationContext(new ClassPathXmlApplicationContext(
						"classpath:applicationContext.xml"));
		WPManager manager = new WPManager();
		try {
			// добавление поста
			Post p = Post.createDefaultPost();

			User author = (User) userManager.getById(1L);
			p.setPostAuthor(author);
			p.setGuid("guid1");
			p.setPinged("");
			p.setPostContentFiltered("");
			p.setPostExcerpt("test PostExcerpt");
			p.setPostName("test PostName");
			p.setPostTitle("test PostTitle");
			p.setPostMimeType("");
			p.setPostContent("<h2>test</h2> - 'PostContent'");
			p.setPostType(PostType.post);

			manager.saveOrUpdatePost(p);
		} catch (ServiceException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

}
