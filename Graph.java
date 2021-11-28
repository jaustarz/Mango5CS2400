public class Graph<T>
{
    private boolean[][] edges;
    private T[] labels;

    public Graph(int n)
    {
        edges = new boolean[n][n];
        labels = (T[]) new Object[n];
    }

    public T getlabel(int vertex)
    {
        return labels[vertex];
    }

    public boolean isEdge(int source, int target)
    {
        return edges[source][target] = true;
    }

    public void addEdge(int source, int target)
    {
        edges[source][target] = true;
    }

    public int[] neighbors(int vertex)
    {
        int i;
        int count = 0;
        int[] answer;

        for(i = 0; i < labels.length; i++)
        {
            if(edges[vertex][i])
            {
                count++;
            }
        }
        answer = new int[count];
        count = 0;
        for(i = 0; i<labels.length; i++)
        {
            if(edges[vertex][i])
            {
                answer[count++] = i;
            }
        }
        return answer;
    }

    public void removeEdge(int source, int target)
    {
        edges[source][target] = false;
    }

    public void setLabel(int vertex, T newLabel)
    {
        labels[vertex] = newLabel;
    }

    public int size()
    {
        return labels.length;
    }

    public QueueInterface<T> getBreadthFirstTraversal(T origin)
    {
        resetVertices();
        QueueInterface<T> traversalOrder = new LinkedQueue<T>();
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<VertexInterface<T>>();

        VertexInterface<T> originVertex = vertices.getValue(origin);
        originVertex.visit();
        traversalOrder.enqueue(origin);
        vertexQueue.enqueue(originVertex);

        while(!vertexQueue.isEmpty())
        {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();

            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();

            while(neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if(!nextNeighbor.isVisited())
                {
                    nextNeighbor.visit();
                    traversalOrder.enqueue(nextNeighbor.getLabel());
                    vertexQueue.enqueue(nextNeighbor);
                }
            }
        }
        return traversalOrder;
    }
}
