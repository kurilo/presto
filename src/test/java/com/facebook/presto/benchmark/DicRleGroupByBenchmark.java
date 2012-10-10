package com.facebook.presto.benchmark;

import com.facebook.presto.block.TupleStream;
import com.facebook.presto.block.TupleStreamSerdes;
import com.facebook.presto.operator.GroupByOperator;
import com.facebook.presto.tpch.TpchSchema;

import java.util.List;

public class DicRleGroupByBenchmark
        extends AbstractTupleStreamBenchmark
{
    public DicRleGroupByBenchmark()
    {
        super("groupby_dic_rle", 10, 50);
    }


    @Override
    protected void setUp()
    {
        loadColumnFile(TpchSchema.Orders.ORDERSTATUS, TupleStreamSerdes.Encoding.DICTIONARY_RLE);
    }

    @Override
    protected TupleStream createBenchmarkedTupleStream(List<? extends TupleStream> inputTupleStreams)
    {
        return new GroupByOperator(inputTupleStreams.get(0));
    }

    public static void main(String[] args)
    {
        new DicRleGroupByBenchmark().runBenchmark(
                new SimpleLineBenchmarkResultWriter(System.out)
        );
    }
}