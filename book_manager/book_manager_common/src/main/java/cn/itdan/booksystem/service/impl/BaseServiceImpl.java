package cn.itdan.booksystem.service.impl;

import cn.itdan.booksystem.pojo.BasePojo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 常用CRUD操作实现层
 */
public abstract class BaseServiceImpl<T extends BasePojo>  {


   @Autowired
    private BaseMapper<T> baseMapper;

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    public T queryById(Long id) {
        return this.baseMapper.selectById(id);
    }
    /**
     * 查询所有数据
     *
     * @return
     */
    public List<T> queryAll() {
        return this.baseMapper.selectList(null);
    }

    /**
     * 根据条件查询一条数据
     *
     * @param queryWrapper 查询对象的条件
     * @return
     */
    public T queryByWhere(QueryWrapper<T> queryWrapper) {
        return this.baseMapper.selectOne(queryWrapper);
    }

    /**
     * 根据条件查询一条数据
     *
     * @param record 查询对象的类型
     * @return
     */
    public T queryOne(T record) {
        return this.baseMapper.selectOne(new QueryWrapper<>(record));
    }
    /**
     * 根据条件查询数据列表
     *
     * @param record 查询对象的类型
     * @return
     */
    public List<T> queryListByWhere(T record) {
        return this.baseMapper.selectList(new QueryWrapper<>(record));
    }
    /**
     * 根据条件分页查询数据列表
     *
     * @param record 查询对象的类型
     * @param page
     * @param rows
     * @return
     */
    public IPage<T> queryPageListByWhere(T record, Integer page, Integer rows) {
       // 获取分页数据
        return this.baseMapper.selectPage(new Page<>(page, rows), new QueryWrapper<>
                (record));
    }

    /**
     * 根据条件分页查询数据列表
     *
     * @param queryWrapper
     * @param page
     * @param rows
     * @return
     */
    public IPage<T> queryPageList(QueryWrapper<T> queryWrapper, Integer page,
                                  Integer rows) {
        // 获取分页数据
        return this.baseMapper.selectPage(new Page<T>(page, rows), queryWrapper);
    }

    /**
     * 保存数据
     *
     * @param record 查询对象的类型
     * @return
     */
    public Integer save(T record) {
        record.setCreated(new Date());
        record.setUpdated(record.getCreated());
        return this.baseMapper.insert(record);
    }
    /**
     * 更新数据
     *
     * @param record 查询对象的类型
     * @return
     */
    public Integer update(T record) {
        record.setUpdated(new Date());
        return this.baseMapper.updateById(record);
    }
    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    public Integer deleteById(Long id) {
        return this.baseMapper.deleteById(id);
    }
    /**
     * 根据ids批量删除数据
     *
     * @param ids
     * @return
     */
    public Integer deleteByIds(List<Long> ids) {
        return this.baseMapper.deleteBatchIds(ids);
    }
    /**
     * 根据条件删除数据
     *
     * @param record 查询对象的类型
     * @return
     */
    public Integer deleteByWhere(T record){
        return this.baseMapper.delete(new QueryWrapper<>(record));
    }



}
