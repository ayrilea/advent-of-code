package site.ayrilea.advent.solution.year2025.day11;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;

import static site.ayrilea.advent.solution.year2025.day11.Shared.parseInput;
import static site.ayrilea.advent.solution.year2025.day11.Shared.pathsBetween;

@SolutionMetadata(year = 2025, day = 11, part = 2)
public class Part2 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        var devices = parseInput(input);

        long svrToFft = pathsBetween(devices.get("svr"), devices.get("fft"));
        long svrToDac = pathsBetween(devices.get("svr"), devices.get("dac"));
        long dacToFft = pathsBetween(devices.get("dac"), devices.get("fft"));
        long fftToDac = pathsBetween(devices.get("fft"), devices.get("dac"));
        long dacToOut = pathsBetween(devices.get("dac"), devices.get("out"));
        long fftToOut = pathsBetween(devices.get("fft"), devices.get("out"));

        return svrToFft * fftToDac * dacToOut + svrToDac * dacToFft * fftToOut;
    }
}