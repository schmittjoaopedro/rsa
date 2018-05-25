require(ggplot2)
require(dplyr)

setwd("/home/joao/projects/master-degree/rsa/results")

bruteData = read.csv(file = "results-brute.csv", sep = ";")
pollardData = read.csv(file = "results-pollard.csv", sep = ";")

bruteData = bruteData %>% 
    group_by(key_size) %>% 
    summarise(
        generate_keys_mean = mean(generate_keys) / 1000,
        encrypt_mean = mean(encrypt) / 1000,
        decrypt_mean = mean(decrypt) / 1000,
        brute_force_mean = mean(brute_force) / 1000
    )

pollardData = pollardData %>% 
    group_by(key_size) %>% 
    summarise(
        generate_keys_mean = mean(generate_keys) / 1000,
        encrypt_mean = mean(encrypt) / 1000,
        decrypt_mean = mean(decrypt) / 1000,
        brute_force_mean = mean(brute_force) / 1000
    )

allDataMerged = merge(data.frame(pollardData, row.names=NULL), data.frame(bruteData, row.names=NULL), by = "key_size", all = TRUE, suffixes = c("_pollard", "_brute"))

if(plotType == "all_brute") {
    gg <- ggplot(bruteData, aes(key_size))
    gg <- gg + ggtitle("All Brute Force")
    gg <- gg + xlab("Key size")
    gg <- gg + ylab("Time (s)")
    gg <- gg + geom_line(aes(y = generate_keys_mean, color = "Generate Keys"))
    gg <- gg + geom_line(aes(y = encrypt_mean, color = "Encrypt"))
    gg <- gg + geom_line(aes(y = decrypt_mean, color = "Decrypt"))
    gg <- gg + geom_line(aes(y = brute_force_mean, color = "Brute"))
    gg
} else if (plotType == "all_pollard") {
    gg <- ggplot(pollardData, aes(key_size))
    gg <- gg + ggtitle("All Pollard Force")
    gg <- gg + xlab("Key size")
    gg <- gg + ylab("Time (s)")
    gg <- gg + geom_line(aes(y = generate_keys_mean, color = "Generate Keys"))
    gg <- gg + geom_line(aes(y = encrypt_mean, color = "Encrypt"))
    gg <- gg + geom_line(aes(y = decrypt_mean, color = "Decrypt"))
    gg <- gg + geom_line(aes(y = brute_force_mean, color = "Pollard"))
    gg
} else if (plotType == "all_force") {
    gg <- ggplot(allDataMerged, aes(key_size))
    gg <- gg + ggtitle("Break Force Times")
    gg <- gg + xlab("Key size")
    gg <- gg + ylab("Time (s)")
    gg <- gg + geom_line(aes(y = brute_force_mean_brute, color = "Brute"))
    gg <- gg + geom_line(aes(y = brute_force_mean_pollard, color = "Pollard"))
    gg
} else if (plotType == "all_generate_keys") {
    gg <- ggplot(allDataMerged, aes(key_size))
    gg <- gg + ggtitle("All Generate Keys")
    gg <- gg + xlab("Key size")
    gg <- gg + ylab("Time (s)")
    gg <- gg + geom_line(aes(y = generate_keys_mean_brute, color = "Brute"))
    gg <- gg + geom_line(aes(y = generate_keys_mean_pollard, color = "Pollard"))
    gg
} else if (plotType == "all_encrypt") {
    gg <- ggplot(allDataMerged, aes(key_size))
    gg <- gg + ggtitle("All Encrypt")
    gg <- gg + xlab("Key size")
    gg <- gg + ylab("Time (s)")
    gg <- gg + geom_line(aes(y = encrypt_mean_brute, color = "Brute"))
    gg <- gg + geom_line(aes(y = encrypt_mean_pollard, color = "Pollard"))
    gg
} else if (plotType == "all_decrypt") {
    gg <- ggplot(allDataMerged, aes(key_size))
    gg <- gg + ggtitle("All Decrypt")
    gg <- gg + xlab("Key size")
    gg <- gg + ylab("Time (s)")
    gg <- gg + geom_line(aes(y = decrypt_mean_brute, color = "Brute"))
    gg <- gg + geom_line(aes(y = decrypt_mean_pollard, color = "Pollard"))
    gg
} else if (plotType = "all_rsa") {
    gg <- ggplot(pollardData, aes(key_size))
    gg <- gg + ggtitle("RSA")
    gg <- gg + xlab("Key size")
    gg <- gg + ylab("Time (s)")
    gg <- gg + geom_line(aes(y = generate_keys_mean, color = "Generate Keys"))
    gg <- gg + geom_line(aes(y = encrypt_mean, color = "Encrypt"))
    gg <- gg + geom_line(aes(y = decrypt_mean, color = "Decrypt"))
    gg
}