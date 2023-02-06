package com.example.datastructureandalgorithms.datastructure.HuffmanCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {

    public static void main(String[] args) {

//        String content = "i like like like java do you like a java kkk yhh";
//        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length);
//
//        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
//        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));
//        byte[] sourceByte = decode(huffmanCodes, huffmanCodeBytes);
//        System.out.println("原来的字符串：" + new String(sourceByte));

//        //测试压缩文件
//        String srcFile = "f://huffman//fruit.jpg";
//        String destFile = "f://huffman//dest.zip";
//        zipFile(srcFile, destFile);
//        System.out.println("压缩后的文件~");
//
//        //测试解压文件
//        String zipFile = "f://huffman//dest.zip";
//        destFile = "f://huffman//dest.jpg";
//        unZipFile(zipFile, destFile);
//        System.out.println("解压成功！");

        //测试压缩文件
        String srcFile = "f://huffman//text.txt";
        String destFile = "f://huffman//dest.txt";
        zipFile(srcFile, destFile);
        System.out.println("压缩后的文件~");

        //测试解压文件
        String zipFile = "f://huffman//dest.txt";
        destFile = "f://huffman//source.txt";
        unZipFile(zipFile, destFile);
        System.out.println("解压成功！");
    }

    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //System.out.println("nodes=" + nodes);
        //根据node创建哈夫曼树
        //System.out.println("哈夫曼树：");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //huffmanTreeRoot.preOrder();
        //将生产的哈夫曼树生产对应的哈夫曼编码表
        getCodes(huffmanTreeRoot, "", stringBuilder);
        //System.out.println("生成的哈夫曼编码表：" + huffmanCodes);
        //根据生成的哈夫曼编码，得到压缩后的哈夫曼编码字节数组  01110011
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    /**
     * 对压缩文件进行解压
     *
     * @param zipFile
     * @param destFile
     */
    public static void unZipFile(String zipFile, String destFile) {
        //1.先定义文件的输入流
        FileInputStream is = null;
        //2.定义一个对象输入流
        ObjectInputStream ois = null;
        //3.定义一个文件输出流
        OutputStream os = null;

        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取哈夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            System.out.println("解码开始");
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            System.out.println("解码结束");
            //将byte数组写入到目标文件
            os = new FileOutputStream(destFile);
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * 对一个文件进行压缩
     *
     * @param srcFile
     * @param destFile
     */
    public static void zipFile(String srcFile, String destFile) {

        //1.创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //2.创建文件的输入流
        FileInputStream is = null;
        try {
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小的byte数组
            byte[] b = new byte[is.available()];
            is.read(b);
            //获取到文件对应的哈夫曼编码表 huffmanZip 返回的是压缩后的最后一个字节可能不满8位
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(destFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            //这里以对象流的方式写入哈夫曼编码，是为了以后恢复源文件时使用
            //注意：一定要把哈夫曼编码写入压缩文件 不然无法解码
            oos.writeObject(huffmanCodes);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int zeroCount = 0;

    /**
     * 负数：符号位【0：正号，1为负号】+原码
     * 补码、原码、反码
     * 反码：正数的反码还是自己，负数的反码是除符号位外，按位取反（即除了最高位不变外，其余低位取反）
     * 补码：正数的补码还是自己，负数的补码等于反码+1
     *
     * @param bytes
     * @param huffmanCodes
     * @return
     */
    //将字符串对应的byte[]数组，通过生成的哈夫曼编码表，返回一个哈夫曼编码压缩后的byte[]
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        //1、利用huffmanCodes将bytes转成哈夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //转成对应的字符串后需要进行压缩
        int len;
        System.out.println("文件大小：" + stringBuilder.length());
        if (stringBuilder.length() % 8 == 0) {//对应的字符串长度刚好是8的倍数
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录第几个byte
        System.out.println(huffmanCodes);
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);//因为最后一个字节不满8位
                System.out.println("integer="+strByte);
                for (int j = 0; j < strByte.length()-1; j++) {//不管最后一位是0还是1，只计算前面的0位个数
                    if (strByte.charAt(j) == '0') {
                        zeroCount++;
                    } else {
                        break;
                    }
                }
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转成一个byte,huffmanCodes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * 对压缩数据进行解码
     *
     * @param huffmanCodes
     * @param huffmanBytes
     * @return
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1.huffmanBytes对应的二进制字符串
        StringBuilder stringBuilder1 = new StringBuilder();
        //2.将byte数组转成二进制字符串
        System.out.println("decode :" + huffmanBytes.length);
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //最后一个字节可能不满8位
            //最后一个字节的时候 flag=true
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder1.append(byteToString(!flag, b));
        }
        //System.out.println("stringBuilder1="+stringBuilder1);
        //把二进制字符串按照指定的哈夫曼编码进行解码
        //把哈夫曼编码表调换，反向查询
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());//因为哈夫曼编码没有二义性
        }
        System.out.println("map=" + map);
        List<Byte> list = new ArrayList<>();
        //扫描解码后的二进制字符串 for循环不需要i++，因为找到一个匹配的字符串后，i直接跳到count+1了
        Byte b = null;
        String key = null;
        String strByte;
        for (int i = 0; i < stringBuilder1.length(); ) {
            int count = 1;
            boolean flag = true;
            while (flag) {
                //取出一个’1‘，’0‘
                //101010001011111111....
                key = stringBuilder1.substring(i, i + count);

                b = map.get(key);
                if (b == null) {//没有匹配到
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        //当for循环结束后 就查找完了
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    private static List<Node> getNodes(byte[] bytes) {

        //1.创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();

        //遍历bytes,统计每一个byte出现的次数（即每个字符出现的次数）-》map[key,value] 字符作为key,统计的次数作为value（权值）
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }

        //把每一个键值对转换成一个Node对象，并加入到nodes集合中
        //遍历map
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过list集合构建一颗哈夫曼树 最优二叉树 即：重新得到各个字符对应的哈夫曼二进制编码
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //1.先从小到大排序
            Collections.sort(nodes);
            //2.取出第一课最小的二叉树
            Node leftNode = nodes.get(0);
            //3.取出第二课最小的二叉树
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树,他的根节点，没有data,只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }

        return nodes.get(0);
    }

    /**
     * 将byte转字符串
     *
     * @param flag
     * @param b
     * @return
     */
    private static String byteToString(boolean flag, byte b) {
        int temp = b;//将b转为int
        //flag==false => 表明最后一个字节
        if (flag) {//因为最后一个字节可能不满8位，按位或就没有意义
            //如果是正数，我们还需要补高位
            temp |= 256;// 1 0000 0000 | 0000 0001 => 1 0000 0001
        }

        String str = Integer.toBinaryString(temp);
        //最后一个字节是正数的话可以直接返回
        //System.out.println("str=" + str);
        //因为负数的话一定是32位 所以要判断一下最后一位是否为负数
        if (flag || temp < 0) {
            return str.substring(str.length() - 8);
        } else {
            for (int i = 0; i < zeroCount; i++) {
                str = "0" + str;
            }
            System.out.println("最后一个字节："+b);
            System.out.println("str："+str);
            return str;
        }
    }

    //将生产的哈夫曼树生产对应的哈夫曼编码表
    //1.将哈夫曼编码表放在Map<Byte,String>
    //例如：32->01  97->100
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //2.在生产哈夫曼编码表时，需要去拼接路径，定义一个StringBuilder 存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 将传入的node节点的所有叶子节点的哈夫曼编码得到，并放入到map集合中
     *
     * @param node
     * @param code
     * @param stringBuilder
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) {//非叶子节点 数据都是叶子节点
                //递归处理
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else {
                //表示找到叶子节点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈夫曼树为空，不能遍历！");
        }
    }
}

class Node implements Comparable<Node> {
    Byte data;//比如a -> 97
    int weight;//权值 字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {//从小到大排序
        return this.weight - o.weight;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

//    @Override
//    public String toString() {
//        return "Node{" +
//                "data=" + data +
//                ", weight=" + weight +
//                '}';
//    }
}