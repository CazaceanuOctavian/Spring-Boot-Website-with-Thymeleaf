package com.ltp.workbook.Repository;

import java.util.ArrayList;
import java.util.List;

import com.ltp.workbook.Show;

public class ShowRepository {

    private List<Show> showList = new ArrayList<Show>();

    public Show getShow (int index) {
        return showList.get(index);
    }

    public void addShow (Show show) {
        showList.add(show);
    }

    public void setShow (int index, Show show) {
        showList.set(index, show);
    }

    public void removeShowIfFound(Show show) {
        showList.removeIf(n -> (n.getId().equals(show.getId())));
    }

    public int listSize () {
        return showList.size();
    }

    public List<Show> getShows() {
        return showList;
    }


}