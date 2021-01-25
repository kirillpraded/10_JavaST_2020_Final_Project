package by.praded.ask_and_go.service;

import by.praded.ask_and_go.entity.Tag;

import java.util.List;

/**
 * @author Kiryl Praded
 * 06.01.2021
 * <p>
 * Service with methods for actions on tags.
 * @see Tag
 * @see AbstractService
 */
public interface TagService extends AbstractService {
    /**
     * Declaration of method to parse tags from string to list of copies {@link Tag} class.
     *
     * @param tags - string of tags.
     * @return list of parsed tags.
     */
    List<Tag> parseTags(String tags);
}
