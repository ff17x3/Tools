package util;

public class ClockNano implements Runnable {
    private int desiredTicksPerSecond;
    private Tickable toTick;
    private long lastTickDelayNanos = 0, lastTickMethodDelayNanos;
    private double neededDelay;
    private Thread thread;

    public ClockNano(int desiredTicksPerSecond, Tickable toTick) {
        this.desiredTicksPerSecond = desiredTicksPerSecond;
        this.toTick = toTick;

        neededDelay = 1e9 / desiredTicksPerSecond;
        lastTickDelayNanos = Math.round(neededDelay); // first time only
    }

    public synchronized long getDesiredTicksPerSecond() {
        return desiredTicksPerSecond;
    }

    public synchronized void setDesiredTicksPerSecond(int desiredTicksPerSecond) {
        this.desiredTicksPerSecond = desiredTicksPerSecond;
        neededDelay = 1e9 / desiredTicksPerSecond;
    }

    public synchronized double getRealTicksPerSecond() {
        return 1e9 / lastTickDelayNanos;
    }

    private void mainTick() throws Exception {
//        Log.v("Clock", "mainTick() start");
        long timeBegin = System.nanoTime();
        synchronized (this) {
            try {
                toTick.tick(lastTickDelayNanos);
            } catch (Exception e) {
                throw new Exception("Tickable ticked by TickMaker threw this Exception", e);
            }
            lastTickMethodDelayNanos = System.nanoTime() - timeBegin;
            if (neededDelay > lastTickMethodDelayNanos) {
                Thread.sleep(Math.round((neededDelay - lastTickMethodDelayNanos) * 1e-6));
            }

            lastTickDelayNanos = System.nanoTime() - timeBegin;
        }
//        Log.v("Clock", "mainTick() finished");
    }

    @Override
    public void run() {
        try {
            while (!thread.isInterrupted()) {
                mainTick();
            }
        } catch (Exception e) {
            if (!(e instanceof InterruptedException))
                e.printStackTrace();
            thread.interrupt();
        }
    }

    public void startTicking() {

        if (thread == null || !thread.isAlive() || thread.isInterrupted()) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stopTicking() {
        if (thread != null)
            thread.interrupt();
    }

}

