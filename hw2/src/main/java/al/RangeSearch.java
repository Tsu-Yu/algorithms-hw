package al;

public class RangeSearch {
    public int[] searchRange(int[] nums, int target) {
        // STEP1: find the lower bound of target
        int left = lowerBond(nums, target);

        // STEP2: if lower bound is out of range 
        // or the element at lower bound is not target, ex. all elements are greater than target
        if(left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }

        // STEP3: find the upper bound of target
        int right = upperBound(nums, target) - 1;

        return new int[]{left, right};
    }

    public int lowerBond(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;
        // find the first index such that nums[index] >= target
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] >= target) {
                hi = mid; // the lower bound index could be <= mid
            } else {
                lo = mid + 1; // the lower bound index must be > mid
            }
        }
        return lo;
    }

    public int upperBound(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;

        // find the first index such that nums[index] > target
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] > target) {
                hi = mid; // the upper bound index could be <= mid
            } else {
                lo = mid + 1; // the upper bound index must be > mid
            }
        }
        return lo;
    }
}
