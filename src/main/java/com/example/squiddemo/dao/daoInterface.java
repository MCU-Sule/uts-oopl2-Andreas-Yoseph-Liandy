/**
 * @1972015 Andreas Yoseph Liandy
 */

package com.example.squiddemo.dao;

import java.util.List;

public interface daoInterface<E> {
    public int addData(E data);


    public int delData(E data);

    public int updateData(E data);

    public List<E> showData();
}
