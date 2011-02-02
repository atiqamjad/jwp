/**
 *
 */
package com.samsonych;

import is.ida.lib.service.exception.ServiceException;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.samsonych.model.wp.Post;
import com.samsonych.service.Poster;
import com.samsonych.service.WPManager;
import com.samsonych.service.dba.DBManagerFactory;

/**
 * @author samsonov
 * 
 */
public class Main {
    private static Logger LOG = Logger.getLogger(Main.class);

    public static void main(final String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:applicationContext.xml");
        DBManagerFactory.setApplicationContext(applicationContext);

        String gadsDir = AppManagerFactory.getAppConfig().getGadsDir();
        LOG.debug("gadsDir=" + gadsDir);

        File dir = new File(gadsDir);
        String[] nicheDirs = dir.list(DirectoryFileFilter.INSTANCE);
        Arrays.sort(nicheDirs);
        int countPosts = 0;
        for (String niche : nicheDirs) {
            // niche = category

            String nichePath = gadsDir + "/" + niche;
            File nicheFile = new File(nichePath);

            String[] files = nicheFile.list(new FilenameFilter() {
                @Override
                public boolean accept(final File dir, final String name) {
                    return name.matches("^\\d+\\.php$");
                }
            });
            Poster poster = new Poster(niche);
            poster.addNicheCategory();

            WPManager wpManager = new WPManager();
            for (String file : files) {
                String filePath = nichePath + "/" + file;
                Post post = poster.getPostFromFile(new File(filePath));
                LOG.debug(post.toString());
                countPosts++;
                try {
                    wpManager.saveOrUpdatePost(post);
                } catch (ServiceException ex) {
                    LOG.error(String.format("%s not saved!", post), ex);
                }
            }
            LOG.info(String.format("Niche - %s(%d)", niche, files.length));

        }
        LOG.info(String.format("Count posts: %d", countPosts));

    }
}
