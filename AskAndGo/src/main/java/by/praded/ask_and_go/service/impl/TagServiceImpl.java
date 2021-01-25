package by.praded.ask_and_go.service.impl;

import by.praded.ask_and_go.entity.Tag;
import by.praded.ask_and_go.service.TagService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kiryl Praded
 * 06.01.2021
 * <p>
 * Implementation of tag service.
 * @see Tag
 * @see TagService
 * @see Tag
 */
public class TagServiceImpl implements TagService {

    /**
     * Method to parse tags from string to list of {@link Tag}.
     *
     * @param tags - string of tags.
     * @return list of parsed tags.
     */
    @Override
    public List<Tag> parseTags(String tags) {
        String[] tagsArray = tags.split(",");
        List<Tag> tagsList = new ArrayList<>();

        for (String s : tagsArray) {
            if (!s.isEmpty()) {
                Tag tag = new Tag();
                tag.setText(s.trim());
                tagsList.add(tag);
            }
        }

        return tagsList;
    }
}
