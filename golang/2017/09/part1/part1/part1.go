package part1

import "bufio"
import "os"

func GetInput() []byte {
    file, _ := os.Open("../input.txt")
    reader := bufio.NewReader(file)
    line := make([]byte, 0)
    for {
        segment, prefix, _ := reader.ReadLine()
        line = append(line, segment...)
        if !prefix {
            return line
        }
    }
}

func GetScore(input []byte) int {
    var score, level, position int
    in_garbage := false
    for position < len(input) {
        //fmt.Printf("%s %d %d %t\n", string(input[position]), position, score, in_garbage)
        switch input[position] {
        case '{':
            if !in_garbage {
                level++
                score += level
            }
            position++
        case '}':
            if !in_garbage {
                level--
            }
            position++
        case '<':
            in_garbage = true
            position++
        case '>':
            in_garbage = false
            position++
        case '!':
            position += 2
        default:
            position++
        }
    }
    return score
}
